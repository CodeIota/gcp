package gcp_api.gcp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gcp_api.gcp.domain.ProductsPot;

@Service
public class PotServices {

    public void consumeProduct(String product, Integer quantity) {
        
        updateProduct(product, quantity);
        
    }

    public void refillProducs() {

        ObjectMapper mapper = new ObjectMapper();

        List<ProductsPot> listToJsonFile = new ArrayList<>();

        ProductsPot productA = new ProductsPot("A", 60);
        ProductsPot productB = new ProductsPot("B", 30);

        listToJsonFile.add(productA);
        listToJsonFile.add(productB);
        try {
            mapper.writeValue(new File("/home/rubendgomes/Documents/GitHub/gcp/src/main/resources/data/pot.json"), listToJsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    private void updateProduct(String product, Integer quantity) {
        ObjectMapper mapper = new ObjectMapper();
        
        try (InputStream inputStream = new FileInputStream(new File("/home/rubendgomes/Documents/GitHub/gcp/src/main/resources/data/pot.json"))) {
            TypeReference<List<ProductsPot>> typeReference = new TypeReference<List<ProductsPot>> () {};
            List<ProductsPot> products = mapper.readValue(inputStream, typeReference);
            TypeReference<String> typeReference2 = new TypeReference<String>() {};
            String data = mapper.readValue(inputStream, typeReference2);
            System.out.println(data);

            for (ProductsPot p: products) {
                if (p.getProduct().equals(product)) {
                    
                    if (p != null && (p.getQuantity() - quantity >= 0)) {
                        Integer newQuantity = p.getQuantity() - quantity;
                        p.setQuantity(newQuantity);
                        break;
                    } else {
                        // return "{\"message\":failed}";

                    }

                }
            }

            mapper.writeValue(new File("/home/rubendgomes/Documents/GitHub/gcp/src/main/resources/data/pot.json"), products);
            // return "{\"message\":success}";
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
