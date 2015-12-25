package org.hello.security;

import org.hello.model.usuario.Usuario;
import org.hello.model.usuario.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AcessoService implements UserDetailsService {
	
	@Autowired
	private UsuarioDao usuario;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario account = (Usuario) usuario.select("username", username).get(0);
		
		if(account==null) {
			System.out.println("No such user: " + username);
			throw new UsernameNotFoundException("No such user: " + username);
		} else if (account.getAuthorities().isEmpty()) {
			System.out.println("User " + username + " has no authorities");
			throw new UsernameNotFoundException("User " + username + " has no authorities");
		}
		
		return new User(account.getUsername(), account.getPassword(), account.isEnabled(), account.isAccountNonExpired(), account.isAccountNonExpired(), account.isAccountNonLocked(), account.getAuthorities());
	}

}
