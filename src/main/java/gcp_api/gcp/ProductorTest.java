package gcp_api.gcp;

import gcp_api.gcp.services.ReplicationClients;

public class ProductorTest {
    
    public static void main(String[] args) {
        ReplicationClients productor = new ReplicationClients(2509);
        productor.run();
    }
}
