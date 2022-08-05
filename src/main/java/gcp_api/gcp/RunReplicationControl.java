package gcp_api.gcp;

import gcp_api.gcp.services.ReplicationControl;

public class RunReplicationControl {
    public static void main(String[] args) {
        try {
            System.out.println(" -- Running replication control server --");
            ReplicationControl control = new ReplicationControl();
		    control.run();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
