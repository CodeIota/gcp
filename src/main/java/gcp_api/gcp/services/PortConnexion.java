package gcp_api.gcp.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PortConnexion {
    protected Socket socket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;

    public PortConnexion(String servidor, int puerto){
        try {
            this.socket = new Socket(servidor, puerto);
            this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.inputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean ReadBoolean() throws IOException {
        return this.inputStream.readBoolean();
    }

    public void SendBoolean(boolean mensaje) throws IOException {
        this.outputStream.writeBoolean(mensaje);
    }

    public void SendString(String message) throws IOException {
        this.outputStream.writeUTF(message);
    }

    public String ReadString() throws IOException {
        return this.inputStream.readUTF();
    }

    public void Close() throws IOException {
        this.outputStream.close();
    }

}
