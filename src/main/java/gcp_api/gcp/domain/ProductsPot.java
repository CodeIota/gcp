package gcp_api.gcp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsPot {
    
    private String product;
    private Integer quantity;

    ProductsPot () {}

}
