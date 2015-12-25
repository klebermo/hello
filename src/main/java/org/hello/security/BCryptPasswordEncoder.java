package org.hello.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoder implements PasswordEncoder {

	public String encode(CharSequence rawPassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(rawPassword.toString().getBytes());
			byte[] digest = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < digest.length; i++)
				sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(encode(rawPassword));
	}

}
