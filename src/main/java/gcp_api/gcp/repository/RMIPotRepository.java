package gcp_api.gcp.repository;

import java.io.IOException;

public interface RMIPotRepository {
    
    public String fill();
    public String consume(String product, Integer quantity);
    public String consult();
    public void save() throws IOException;
    public void recover() throws IOException;
    public String index();
    

}
