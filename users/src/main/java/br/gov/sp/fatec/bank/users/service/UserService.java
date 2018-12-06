package br.gov.sp.fatec.bank.users.service;

import br.gov.sp.fatec.bank.users.model.Authority;
import br.gov.sp.fatec.bank.users.model.User;
import br.gov.sp.fatec.bank.users.repository.AuthorityRepository;
import br.gov.sp.fatec.bank.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@PreAuthorize("hasRole('ROLE_HIGH_MANAGER')")
	public User newUser(User user) {
		if(user.getUsername().trim().isEmpty()) {
			throw new RuntimeException("Username can not be empty.");
		}
		if (this.userRepository.findByUsername(user.getUsername()) != null) {
			throw new RuntimeException("Username already exists");
		}
		return this.userRepository.saveAndFlush(user);
	}

	private void validatePassword(String password) {
		/*
		^                 # start-of-string
		(?=.*[0-9])       # a digit must occur at least once
		(?=.*[a-z])       # a lower case letter must occur at least once
		(?=.*[A-Z])       # an upper case letter must occur at least once
		(?=\S+$)          # no whitespace allowed in the entire string
		.{8,}             # anything, at least eight places though
		$                 # end-of-string
		* */
		if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
			throw new RuntimeException("Password not acceptable");
		}
	}

	@PreAuthorize("hasRole('ROLE_HIGH_MANAGER')")
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.findByUsername(username);
	}
}
