package br.gov.sp.fatec.bank.clients.service;

import br.gov.sp.fatec.bank.clients.model.Client;
import br.gov.sp.fatec.bank.clients.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	@PreAuthorize("hasAnyRole('ROLE_HIGH_MANAGER','ROLE_MANAGER', 'ROLE_CASHIER')")
	public Client findBySsn(String ssn) {
		if(ssn.isEmpty()) {
			throw new RuntimeException("Social Security Number is required.");
		}
		return this.clientRepository.findBySsn(ssn);
	}

	@PreAuthorize("hasAnyRole('ROLE_HIGH_MANAGER','ROLE_MANAGER', 'ROLE_CASHIER')")
	public List<Client> findByName(String name) {
		if(name.isEmpty()) {
			throw new RuntimeException("Name is required.");
		}
		return this.clientRepository.findByNameContainingIgnoreCase(name);
	}

	@PreAuthorize("hasAnyRole('ROLE_HIGH_MANAGER','ROLE_MANAGER')")
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
