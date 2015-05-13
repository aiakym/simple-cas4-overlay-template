package com.startjg.crp.sso.encoder;

import org.jasig.cas.authentication.handler.PasswordEncoder;

public class BCryptPasswordEncoder implements PasswordEncoder {

	private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder;

	public void setbCryptPasswordEncoder(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public String encode(String password) {
		return this.bCryptPasswordEncoder.encode(password);
	}

}
