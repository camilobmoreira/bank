package br.gov.sp.fatec.bank.users.security;


import br.gov.sp.fatec.bank.users.model.User;
import br.gov.sp.fatec.bank.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private UserRepository usuarioRepository;

	protected JWTLoginFilter(String url, AuthenticationManager authManager, UserRepository userRepository) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.usuarioRepository = userRepository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		User credentials = new ObjectMapper()
				.readValue(request.getInputStream(), User.class);

		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(),
						credentials.getPassword(),
						Collections.emptyList()
				)
		);
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		User user = this.usuarioRepository.findByUsername(auth.getName());
		TokenAuthenticationService.addAuthentication(response, new Gson().toJson(user, User.class));
	}

}
