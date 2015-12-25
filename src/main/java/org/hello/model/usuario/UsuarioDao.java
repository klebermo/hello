package org.hello.model.usuario;

import org.hello.generic.persistence.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDao extends Dao<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}

}
