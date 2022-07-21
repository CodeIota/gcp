package gcp_api.gcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import gcp_api.gcp.repository.RMIPotRepository;

@SpringBootApplication
public class GcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpApplication.class, args);

		// System.out.println(rmiRepository.index());
	}

	@Deprecated
	@Bean
	RmiProxyFactoryBean rmiProxy() {
		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
		bean.setServiceInterface(RMIPotRepository.class);
		bean.setServiceUrl("rmi://localhost:1099/pot");
		return bean;
	}


}
