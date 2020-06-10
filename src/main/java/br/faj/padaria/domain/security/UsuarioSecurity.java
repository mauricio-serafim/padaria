package br.faj.padaria.domain.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSecurity extends User {

	public UsuarioSecurity(String login, String senha, Boolean ativo,
			Collection<? extends GrantedAuthority> authorities) {
		super(login, senha, ativo, true, true, true, authorities);
	}
}
