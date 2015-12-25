package org.hello.model.permissao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hello.generic.persistence.Model;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permissao extends Model implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867748913160844408L;
	
	@Id
	private String id;
	
	@Column
	private String authority;

	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return authority;
	}

}
