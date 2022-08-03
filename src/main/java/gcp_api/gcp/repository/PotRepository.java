package gcp_api.gcp.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

// import gcp_api.gcp.domain.ProductsPot;
import gcp_api.gcp.services.PotServices;

public class PotRepository implements RMIPotRepository {

    private PotServices potServices = new PotServices();

    @Override
    public synchronized String fill() {
        potServices.refillProducs();
        return "{message: success}";
    }

    @Override
    @Autowired
    public synchronized String consume(String product, Integer quantity) {
        try {
            potServices.consumeProduct(product, quantity);
            return "consumed";
        } catch (Exception e) {
            System.out.println(e.toString() + " fallo en el repositorio");
            return null;
        }
    }

    @Override
    public synchronized String consult()  {
        try {
            return potServices.jsonToString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public synchronized String save()  {
        return "save";
    }

    @Override
    public synchronized String recover()  {
        return "recover";
    }

    @Override
    public synchronized String index() {
        return "Hello, world!";
    }
    
}
