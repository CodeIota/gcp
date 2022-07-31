package gcp_api.gcp.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ReplicationClients {
    private static final int upperrange = 1 + 1; //sumar uno al rango
    protected ServerSocket serverSocket;
    protected Socket socket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;

    /**
     * @param Port puerto de acceso para los clientes, 2508 para consumidor y 2509 para productor
     */
    public ReplicationClients(int Port) throws IOException {
        this.serverSocket = new ServerSocket(Port);
    }

    public void Run() throws IOException {
        while(true){
            socket = new Socket();
            this.socket = this.serverSocket.accept();
            this.inputStream = new ObjectInputStream(this.socket.getInputStream());
            this.outputStream = new ObjectOutputStream((this.socket.getOutputStream()));

            this.outputStream.writeBoolean(generateRandomBool());
            if (this.inputStream.readBoolean()){
                String filecontent = this.inputStream.readUTF();
                //Guardar File...
            }
            outputStream.close();
        }
    }

    public boolean generateRandomBool() {
        Random r = new Random();
        return r.nextInt(upperrange) == 1;
    }
}
