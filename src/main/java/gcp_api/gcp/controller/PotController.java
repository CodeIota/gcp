package gcp_api.gcp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
	public void save() {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);

		try {
			rmiRepository.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/consume/{product}/{quantity}")
	public String consume(@PathVariable String product, @PathVariable Integer quantity) {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);
		return rmiRepository.consume(product, quantity);
	}

	@GetMapping("/recover")
	public void recover() {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);

		try {
			rmiRepository.recover();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/consult")
	public String consult() {
		RMIPotRepository rmiRepository = appContext.getBean(RMIPotRepository.class);

		return rmiRepository.consult();
	}

}