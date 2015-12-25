package org.hello.model.usuario;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hello.generic.persistence.Model;
import org.hello.model.autorizacao.Autorizacao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario extends Model implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2793242992941553301L;
	
	@Id
	private String id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Autorizacao> autorizacoes;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}

	public boolean isAccountNonExpired() {
		return false;
	}

	public boolean isAccountNonLocked() {
		return false;
	}

	public boolean isCredentialsNonExpired() {
		return false;
	}

	public boolean isEnabled() {
		return false;
	}

	public Collection<Autorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(Collection<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}
