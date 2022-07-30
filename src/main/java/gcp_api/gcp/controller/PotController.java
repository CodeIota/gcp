package gcp_api.gcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gcp_api.gcp.domain.ProductsPot;
import gcp_api.gcp.repository.RMIPotRepository;

@RestController
public class PotController {

	@Autowired
	private ApplicationContext appContext;

	@GetMapping("/")
	public String index() {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);
		return rmiRepository.index();
	}

	@GetMapping("/fill")
	public String fill() {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);

		return rmiRepository.fill();
	}

	@GetMapping("/save")
	public String save() {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);

		return rmiRepository.save();
	}

	@GetMapping("/consume/{product}/{quantity}")
	public String consume(@PathVariable String product, @PathVariable Integer quantity) {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);
		return rmiRepository.consume(product, quantity);
	}

	@GetMapping("/recover")
	public String recover() {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);

		return rmiRepository.recover();
	}

	@GetMapping("/consult")
	public ProductsPot consult() {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);

		return rmiRepository.consult();
	}

}