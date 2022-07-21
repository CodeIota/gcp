package gcp_api.gcp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gcp_api.gcp.domain.ProductsPot;

@Service
public class PotServices {

    public String consumeProduct(String product, Integer quantity) {

        return searchProduct(product);

        // System.out.println("product=" + product + " --- quantity=" + quantity);

        // return "product=" + product + " quantity=" + quantity;
        // return "----done----";
    }
    
    private String searchProduct(String product) {
        ObjectMapper mapper = new ObjectMapper();
        ProductsPot returnedProduct = null;
        String message = "";
        
        try (InputStream inputStream = new FileInputStream(new File("/home/rubendgomes/Documents/GitHub/gcp/src/main/resources/data/pot.json"))) {
            TypeReference<List<ProductsPot>> typeReference = new TypeReference<List<ProductsPot>> () {};
            List<ProductsPot> products = mapper.readValue(inputStream, typeReference);


            for (ProductsPot p: products) {
                if (p.getProduct().equals(product)) {
                    returnedProduct = p;
                    break;
                }
            }
            message = "product " + returnedProduct.getProduct() + "quantity" + returnedProduct.getQuantity();
            return message;
        } catch (IOException e) {
            return e.toString();
        }
    }

}
