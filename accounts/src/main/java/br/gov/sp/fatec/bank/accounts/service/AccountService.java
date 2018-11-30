package br.gov.sp.fatec.bank.accounts.service;

import br.gov.sp.fatec.bank.accounts.model.Account;
import br.gov.sp.fatec.bank.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@PreAuthorize("hasAnyRole('ROLE_HIGH_MANAGER','ROLE_MANAGER', 'ROLE_CASHIER')")
	public Account findAccountByNumber(Long number) {
		if(number == null) {
			return null;
		}
		return this.accountRepository.findByNumber(number);
	}

	@PreAuthorize("hasAnyRole('ROLE_HIGH_MANAGER','ROLE_MANAGER')")
	public Account newAccount(Long clientId) {
		if(clientId == null || clientId <= 0l) {
			return null;
		}
		//TODO: validate on Clients Services if clientId exists
		Account account = new Account();
		account.setClientId(clientId);
		int hashCode = LocalDateTime.now().hashCode();
		account.setNumber(hashCode < 0 ? Integer.valueOf(hashCode * -1).longValue() : Integer.valueOf(hashCode).longValue());
		return this.accountRepository.saveAndFlush(account);
	}

	@PreAuthorize("hasRole('ROLE_CASHIER')")
	public Account deposit(Long number, Double value) {
		Account account = this.prepareOperation(number, value);
		account.deposit(value);
		return this.accountRepository.saveAndFlush(account);
	}

	@PreAuthorize("hasRole('ROLE_CASHIER')")
	public Account withdraw(Long number, Double value) {
		Account account = this.prepareOperation(number, value);
		if(value > account.getBalance()) {
			throw new RuntimeException("Sorry. Your balance is not enough.");
		}
		account.withdraw(value);
		return this.accountRepository.saveAndFlush(account);
	}

	private Account prepareOperation(Long number, Double value) {
		if(value == null || value <= 0){
			return null;
		}
		return this.findAccountByNumber(number);
	}
}
