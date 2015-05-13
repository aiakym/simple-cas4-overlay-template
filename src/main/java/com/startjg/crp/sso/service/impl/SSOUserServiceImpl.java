package com.startjg.crp.sso.service.impl;

import com.startjg.crp.sso.domain.SSOUser;
import com.startjg.crp.sso.service.SSOUserService;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.BadCredentialsAuthenticationException;

public class SSOUserServiceImpl implements SSOUserService {

	@Override
	public SSOUser getByCredential(UsernamePasswordCredential credential) throws BadCredentialsAuthenticationException {
		SSOUser user = new SSOUser("test", "test");
		if(credential.getUsername().equals(user.getUsername()) && credential.getPassword().equals(user.getPassword())) {
			return user;
		}
		throw new BadCredentialsAuthenticationException();
	}

}
