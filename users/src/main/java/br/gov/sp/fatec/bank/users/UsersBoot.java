package br.gov.sp.fatec.bank.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UsersBoot {

	public static void main(String[] args) {
		SpringApplication.run(UsersBoot.class, args);
	}

	@GetMapping("/")
	@ResponseBody
	public String home() {
		return "users";
	}
}
