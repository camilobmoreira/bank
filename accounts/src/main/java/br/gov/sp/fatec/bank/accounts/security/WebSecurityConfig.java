package br.gov.sp.fatec.bank.accounts.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Workaround. This should not be done like this
public class WebSecurityConfig extends br.gov.sp.fatec.bank.users.security.WebSecurityConfig {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		super.configure(httpSecurity);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		return super.authenticationProvider();
	}

	@Bean
	public PasswordEncoder encoder() {
		return super.encoder();
	}
}
