package org.hello.model.autorizacao;

import org.hello.generic.persistence.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class AutorizacaoDao extends Dao<Autorizacao> {

	public AutorizacaoDao() {
		super(Autorizacao.class);
	}

}
