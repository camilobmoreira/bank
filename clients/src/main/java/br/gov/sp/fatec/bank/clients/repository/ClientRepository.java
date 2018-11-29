package br.gov.sp.fatec.bank.clients.repository;

import br.gov.sp.fatec.bank.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findBySsn(String ssn);
	List<Client> findByNameContainingIgnoreCase(String name);
}
