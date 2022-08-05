package gcp_api.gcp;

import gcp_api.gcp.services.ReplicationClients;

public class ClientTest {
    public static void main(String[] args) {
        ReplicationClients consumer = new ReplicationClients(2508);
        consumer.run();
        
    }
}
