package br.gov.sp.fatec.bank.accounts.controller;

import br.gov.sp.fatec.bank.accounts.model.Account;
import br.gov.sp.fatec.bank.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Account> findByNumber(@PathVariable(name = "number") Long number) {
		Account account = this.accountService.findAccountByNumber(number);
		if(account == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(account);
	}

	@RequestMapping(value = "/deposit/{number}/{value}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Account> deposit(@PathVariable(name="number") Long number, @PathVariable(name="value") Double value) {
		Account account = this.accountService.deposit(number, value);
		if(account == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(account);
	}

	@RequestMapping(value = "/withdraw/{number}/{value}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Account> withdraw(@PathVariable(name="number") Long number, @PathVariable(name="value") Double value) {
		Account account = this.accountService.withdraw(number, value);
		if(account == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(account);
	}

	@RequestMapping(value = "/{clientId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Account> withdraw(@PathVariable(name="clientId") Long clientId) {
		Account account = this.accountService.newAccount(clientId);
		if(account == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(account);
	}
}
