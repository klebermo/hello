package org.hello.model.usuario;

import org.hello.generic.service.basicService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends basicService<Usuario> {

	public UsuarioService() {
		super(Usuario.class);
	}

}
