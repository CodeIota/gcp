package gcp_api.gcp;

import gcp_api.gcp.services.PortConnexion;

import java.io.IOException;

public class ReplicationServer {
    private static final String Servidor = "localhost";
    private static final int Puerto = 2500;
    private static final String CommandBackUp = "backup";
    private static final String CommandRestore = "restore";

     public static void BackUp() throws IOException {
        PortConnexion conexion = new PortConnexion(Servidor, Puerto);
        conexion.SendString(CommandBackUp);
        conexion.Close();
    }

    public static void Restore() throws IOException {
        PortConnexion conexion = new PortConnexion(Servidor, Puerto);
        conexion.SendString(CommandRestore);
        conexion.Close();
    }
}
