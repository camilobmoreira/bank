package br.gov.sp.fatec.bank.users.repository;

import br.gov.sp.fatec.bank.users.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
