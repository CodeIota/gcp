package gcp_api.gcp.services;

import gcp_api.gcp.ReplicationServer;
import gcp_api.gcp.services.ReplicationControl;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test");
		ReplicationControl control = new ReplicationControl();
		System.out.println("New Success");
		control.run();
		System.out.println("Running");
    }    
}
