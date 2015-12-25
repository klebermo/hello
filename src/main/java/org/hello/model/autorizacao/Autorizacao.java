package org.hello.model.autorizacao;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hello.generic.persistence.Model;
import org.hello.model.permissao.Permissao;

@Entity
public class Autorizacao extends Model {
	
	@Id
	private String id;
	
	@Column
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Permissao> authorities;

	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Permissao> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Collection<Permissao> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return authorities.toString();
	}

}
