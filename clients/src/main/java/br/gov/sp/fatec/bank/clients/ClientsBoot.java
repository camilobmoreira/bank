package br.gov.sp.fatec.bank.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class ClientsBoot {

	public static void main(String[] args) {
		SpringApplication.run(ClientsBoot.class, args);
	}

	@GetMapping("/")
	@ResponseBody
	public String home() {
		return "clients";
	}
}
