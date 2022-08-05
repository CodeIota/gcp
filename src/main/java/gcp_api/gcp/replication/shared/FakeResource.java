package gcp_api.gcp.replication.shared;

public class FakeResource {
    public void commit(){
        System.out.println("\tCOMMITED!");
    }
    public void abort(){
        System.out.println("\tABORTED!");
    }
}
