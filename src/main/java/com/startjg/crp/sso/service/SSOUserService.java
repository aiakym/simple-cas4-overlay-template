package com.startjg.crp.sso.service;

import com.startjg.crp.sso.domain.SSOUser;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.BadCredentialsAuthenticationException;

public interface SSOUserService {

	SSOUser getByCredential(UsernamePasswordCredential credential) throws BadCredentialsAuthenticationException;

}
