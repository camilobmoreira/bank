package br.gov.sp.fatec.bank.clients.security;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service // Workaround. This should not be done like this
public class UserService extends br.gov.sp.fatec.bank.users.service.UserService {
}
