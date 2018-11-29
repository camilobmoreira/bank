package br.gov.sp.fatec.bank.clients.controller;

import br.gov.sp.fatec.bank.clients.model.Client;
import br.gov.sp.fatec.bank.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Client>> findByName(@PathVariable(name = "name") String name) {
		List<Client> clientsList = this.clientService.findByName(name);
		if(clientsList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(clientsList);
	}

	@RequestMapping(value = "/ssn/{ssn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Client> findBySsn(@PathVariable(name = "ssn") String ssn) {
		Client client = this.clientService.findBySsn(ssn);
		if(client == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(client);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Client> newClient(@RequestBody Client client) {
		Client newClient = this.clientService.newClient(client.getSsn(), client.getName());
		if(newClient == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(newClient);
	}
}
