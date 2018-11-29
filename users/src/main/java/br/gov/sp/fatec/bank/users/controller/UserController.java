package br.gov.sp.fatec.bank.users.controller;

import br.gov.sp.fatec.bank.users.model.User;
import br.gov.sp.fatec.bank.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> findByUsername(@PathVariable(name = "username") String username) {
		User user = this.userService.findByUsername(username);
		if(user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> withdraw(@RequestBody User user) {
		if(user == null || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(this.userService.newUser(user.getUsername(), user.getPassword()));
	}
}
