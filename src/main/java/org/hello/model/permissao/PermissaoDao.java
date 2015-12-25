package org.hello.model.permissao;

import org.hello.generic.persistence.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class PermissaoDao extends Dao<Permissao> {

	public PermissaoDao() {
		super(Permissao.class);
	}

}
