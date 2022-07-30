package gcp_api.gcp.repository;

import gcp_api.gcp.domain.ProductsPot;

public interface RMIPotRepository {
    
    public String fill();
    public String consume(String product, Integer quantity);
    public ProductsPot consult();
    public String save();
    public String recover();
    public String index();
    

}
