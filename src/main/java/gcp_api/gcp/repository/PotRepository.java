package gcp_api.gcp.repository;


public class PotRepository implements RMIPotRepository {


    @Override
    public String fill() {
        return "fill";
    }

    @Override
    public String consume() {
        return "consume";
    }

    @Override
    public String consult()  {
        return "consult";
    }

    @Override
    public String save()  {
        return "save";
    }

    @Override
    public String recover()  {
        return "recover";
    }

    @Override
    public String index() {
        return "Hello, world!";
    }
    
}
