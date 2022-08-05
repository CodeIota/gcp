package gcp_api.gcp.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.tomcat.jni.ProcErrorCallback;

public class ReplicationControl extends Thread {

    private static final String CommandBackUp = "backup";
    private static final String CommandRestore = "restore";

    // Enparejado con la lista de puerto, primera direccion con primer puerto
    private final List<String> Servers = Arrays.asList(
            "localhost", //Comsumidor
            "localhost" //Productor
    );

    private final List<Integer> Ports = Arrays.asList(
            2508,
            2509
    );

    private static final int LocalPort = 2500;
    protected ServerSocket serverSocket;
    protected Socket localsocket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;
    protected boolean flag = true;

    private void BackUp() throws Exception {
        ValidatePorts();
        List<PortConnexion> conexiones = new ArrayList<>();
        for (int i = 0; i < Ports.size(); i++){
            System.out.println("estoy aqui con i: " + i);
            conexiones.add(new PortConnexion(Servers.get(i), Ports.get(i)));
            //Se manda el comando de Backup
            conexiones.get(i).SendString(CommandBackUp);
            //Se consulta a cada servidor de replicacion si aprueba (booleano)
            System.out.println("Envio "+CommandBackUp+"Recibi algo?");

            Boolean algo = conexiones.get(i).ReadBoolean();



            if(!algo){

                //Se recibio una respuesta negativa se procede a abortar
                for (int j=0; j<i; j++){

                    //Se aborta el backup y se cierra la conexion
                    conexiones.get(j).SendBoolean(false);
                    conexiones.get(j).Close();
                }
                return;
            }
        } 
        for (int i = 0; i< Servers.size(); i++){
            conexiones.get(i).SendBoolean(true);
            conexiones.get(i).SendString(GetPot());
            conexiones.get(i).Close();
        }
    }

    private void Restore() throws IOException {
        List<String> BackUps = new ArrayList<>();
        for (int i = 0; (i<2) && (i<Ports.size()); i++){
            PortConnexion connexion = new PortConnexion(Servers.get(i), Ports.get(i));
            connexion.SendString(CommandRestore);
            BackUps.add(connexion.ReadString());
            connexion.Close();
        }

        if (Servers.size() < 2) {
            RestorePot(BackUps.get(0));
        }
        else if (Objects.equals(BackUps.get(0), BackUps.get(1))){
            RestorePot(BackUps.get(0));
        }
    }
    
    @Override
    public void run() {
        try{
            if (serverSocket == null) {
                serverSocket = new ServerSocket(LocalPort);
            }
            while (true){
                this.localsocket = null;
                System.out.println("Escuchando");
                this.localsocket = serverSocket.accept();
                System.out.println("Conectado");
                this.inputStream = new ObjectInputStream(this.localsocket.getInputStream());
                this.outputStream = new ObjectOutputStream(this.localsocket.getOutputStream());

                switch (this.inputStream.readUTF()){
                    case CommandBackUp -> BackUp();
                    case CommandRestore -> Restore();
                    default -> System.out.println("Mensaje no Valido");
                }

                this.localsocket.close();
            }                
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private String GetPot(){
        try {
            return new PotServices().jsonToString();
        } catch (IOException e) {
            return "";
        }
    }

    public void RestorePot(String Pot){
        System.out.println(Pot);
    }

    private void ValidatePorts() throws Exception {
        System.out.println("servers:" + Servers);
        System.out.println("ports:" + Ports);
        if(!(Servers.size() == Ports.size())){
            throw new Exception("Error en el numero de puertos y servidores");
        }
    }
}
