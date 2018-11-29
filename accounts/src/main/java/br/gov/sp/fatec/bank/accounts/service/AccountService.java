package br.gov.sp.fatec.bank.accounts.service;

import br.gov.sp.fatec.bank.accounts.model.Account;
import br.gov.sp.fatec.bank.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public Account findAccountByNumber(Long number) {
		if(number == null) {
			return null;
		}
		return this.accountRepository.findByNumber(number);
	}

	@PreAuthorize("hasAnyRole('USER')")
	public Account newAccount(Long clientId) {
		if(clientId == null || clientId <= 0l) {
			return null;
		}
		//TODO: validate on Clients Services if clientId exists
		Account account = new Account();
		account.setClientId(clientId);
		account.setNumber(Integer.valueOf(LocalDateTime.now().hashCode()).longValue());
		return this.accountRepository.saveAndFlush(account);
	}

	public Account deposit(Long number, Double value) {
		Account account = this.prepareOperation(number, value);
		account.deposit(value);
		return this.accountRepository.saveAndFlush(account);
	}

	public Account withdraw(Long number, Double value) {
		Account account = this.prepareOperation(number, value);
		if(value > account.getBalance())
			throw new RuntimeException("Sorry. Your balance is not enough.");
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
