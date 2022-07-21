package gcp_api.gcp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.support.RemoteExporter;

import gcp_api.gcp.repository.PotRepository;
import gcp_api.gcp.repository.RMIPotRepository;

import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class Config {
    
    @Deprecated
    @Bean
    RemoteExporter registerRMIExporter () {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("pot");
        exporter.setServiceInterface(RMIPotRepository.class);
        exporter.setService(new PotRepository());
        exporter.setRegistryPort(1099);
        return exporter;
    }

}
