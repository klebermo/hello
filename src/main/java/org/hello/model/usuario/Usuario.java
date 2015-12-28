package org.hello.model.usuario;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hello.custom.annotations.form.Input;
import org.hello.custom.annotations.form.Select;
import org.hello.custom.annotations.type.Email;
import org.hello.custom.annotations.type.Password;
import org.hello.custom.annotations.type.Text;
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
	@Input
	@Text
	private String username;
	
	@Column
	@Input
	@Password
	private String password;
	
	@Column
	@Input
	@Text
	private String firstName;
	
	@Column
	@Input
	@Text
	private String lastName;
	
	@Column
	@Input
	@Email
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Select
	private Collection<Autorizacao> autorizacao;

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

	public Collection<Autorizacao> getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacoes(Collection<Autorizacao> autorizacao) {
		this.autorizacao = autorizacao;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}
