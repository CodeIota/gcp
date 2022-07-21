package gcp_api.gcp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/fill")
	public String fill() {
		return "Greetings from fill!";
	}

	@GetMapping("/save")
	public String save() {
		return "Greetings from save!";
	}

	@GetMapping("/consume")
	public String consume() {
		return "Greetings from consume!";
	}

	@GetMapping("/recover")
	public String recover() {
		return "Greetings from recover!";
	}

	@GetMapping("/consult")
	public String consult() {
		return "Greetings from consult!";
	}

}