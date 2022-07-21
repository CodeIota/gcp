package gcp_api.gcp.repository;

import org.springframework.beans.factory.annotation.Autowired;

import gcp_api.gcp.services.PotServices;

public class PotRepository implements RMIPotRepository {

    private PotServices potServices = new PotServices();

    @Override
    public synchronized String fill() {
        return "fill";
    }

    @Override
    @Autowired
    public synchronized String consume(String product, Integer quantity) {
        try {
            return potServices.consumeProduct(product, quantity);
        } catch (Exception e) {
            return e.toString() + " fallo en el repositorio";
        }
    }

    @Override
    public synchronized String consult()  {
        return "consult";
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
