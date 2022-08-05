package gcp_api.gcp.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ReplicationClients extends Thread {
    private static final String CommandBackUp = "backup";
    private static final String CommandRestore = "restore";
    private static final int upperrange = 1 + 1; //sumar uno al rango
    private final int Port;
    protected ServerSocket serverSocket;
    protected Socket socket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;

    /**
     * @param port puerto de acceso para los clientes, 2508 para consumidor y 2509 para productor
     */
    public ReplicationClients(int port) {
        Port = port;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(Port);
            while(true){
                this.socket = new Socket();
                this.socket = this.serverSocket.accept();
                this.inputStream = new ObjectInputStream(this.socket.getInputStream());
                this.outputStream = new ObjectOutputStream((this.socket.getOutputStream()));

                switch (this.inputStream.readUTF()){
                    case CommandBackUp -> BackUp();
                    case CommandRestore -> Restore();
                    default -> System.out.println("Mensaje no Valido");
                }

                outputStream.close();
            }
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("explote :)");
        }
    }

    private void BackUp() throws IOException {
        this.outputStream.writeBoolean(generateRandomBool());
        if (this.inputStream.readBoolean()){
            String fileContent = this.inputStream.readUTF();
            //Guardar File...
            System.out.println(fileContent);
        }
    }

    private void Restore() throws IOException {
        this.outputStream.writeUTF(GetBackUp());
    }

    private String GetBackUp(){
        return "";
    }

    private boolean generateRandomBool() {
        Random r = new Random();
        return r.nextInt(upperrange) == 1;
    }
}
