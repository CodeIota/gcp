package gcp_api.gcp;

import gcp_api.gcp.services.ReplicationControl;

public class ReplicationServer {
    public static void main(String[] args) throws Exception {
        ReplicationControl control = new ReplicationControl();
        control.Run();
    }
}
