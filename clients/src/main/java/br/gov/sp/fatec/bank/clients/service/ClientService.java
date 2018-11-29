package br.gov.sp.fatec.bank.clients.service;

import br.gov.sp.fatec.bank.clients.model.Client;
import br.gov.sp.fatec.bank.clients.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	public Client findBySsn(String ssn) {
		if(ssn.isEmpty()) {
			throw new RuntimeException("Social Security Number is required.");
		}
		return this.clientRepository.findBySsn(ssn);
	}

	public List<Client> findByName(String name) {
		if(name.isEmpty()) {
			throw new RuntimeException("Name is required.");
		}
		return this.clientRepository.findByNameContainingIgnoreCase(name);
	}

	public Client newClient(String ssn, String name) {
		if(this.findBySsn(ssn) != null) {
			throw new RuntimeException("Client already exists.");
		}
		Client client = new Client();
		client.setSsn(ssn);
		client.setName(name);
		return this.clientRepository.saveAndFlush(client);
	}
}
