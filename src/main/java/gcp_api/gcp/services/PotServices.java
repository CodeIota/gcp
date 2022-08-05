package gcp_api.gcp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gcp_api.gcp.domain.ProductsPot;

@Service
public class PotServices {

    LogWritter log = new LogWritter();

    public void consumeProduct(String product, Integer quantity) {
        
        updateProduct(product, quantity);
        
    }

    public void refillProducs() {

        ObjectMapper mapper = new ObjectMapper();

        List<ProductsPot> listToJsonFile = new ArrayList<>();

        ProductsPot productA = new ProductsPot("A", 60);
        ProductsPot productB = new ProductsPot("B", 40);

        listToJsonFile.add(productA);
        listToJsonFile.add(productB);

        try {
            log.saveMessageOnFile("pot refilled successully");
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

            for (ProductsPot p: products) {
                if (p.getProduct().equals(product)) {
                    
                    if (p != null && (p.getQuantity() - quantity >= 0)) {
                        Integer newQuantity = p.getQuantity() - quantity;
                        p.setQuantity(newQuantity);
                        log.saveMessageOnFile("client consumed product: " + product + " and quantity: " + quantity + " details: success");

                        break;
                    } else {
                        // return "{\"message\":failed}";
                        log.saveMessageOnFile("client can't consume product: " + product + " and quantity: " + quantity + " details: failed");

                    }

                }
            }

            // System.out.println(data);

            mapper.writeValue(new File("/home/rubendgomes/Documents/GitHub/gcp/src/main/resources/data/pot.json"), products);
            // return "{\"message\":success}";
        } catch (IOException e) {
            e.printStackTrace();
            try {
                log.saveMessageOnFile("server error");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
        }

    }

    public String jsonToString() throws StreamReadException, DatabindException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String data = "";
        InputStream inputStream = new FileInputStream(new File("/home/rubendgomes/Documents/GitHub/gcp/src/main/resources/data/pot.json"));
        TypeReference<List<ProductsPot>> typeReference = new TypeReference<List<ProductsPot>> () {};
        List<ProductsPot> products = mapper.readValue(inputStream, typeReference);

        for (ProductsPot p: products) {
            data += mapper.writeValueAsString(p); 
        }

        log.saveMessageOnFile("client consult current pot product - data: " + data);


        return data;
    }

    public List<ProductsPot> jsonToList () throws StreamReadException, DatabindException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream(new File("/home/rubendgomes/Documents/GitHub/gcp/src/main/resources/data/pot.json"));
        TypeReference<List<ProductsPot>> typeReference = new TypeReference<List<ProductsPot>> () {};
        List<ProductsPot> products = mapper.readValue(inputStream, typeReference);

        return products;
    }


}
