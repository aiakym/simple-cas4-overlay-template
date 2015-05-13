package com.startjg.crp.sso.handler;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;
import java.security.GeneralSecurityException;

public class BCryptUsernamePasswordAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler
		implements InitializingBean {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	@NotNull
	private String fieldUser;

	@NotNull
	private String fieldPassword;

	@NotNull
	private String tableUsers;

	private String sql;

	@Override
	protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential)
			throws GeneralSecurityException, PreventedException {
		final String username = credential.getUsername();
		final String rawPassword = credential.getPassword();
		final String encodedPassword;
		try {
			encodedPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
		} catch (final DataAccessException e) {
			throw new PreventedException("SQL exception while executing query for " + username, e);
		}

		if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
			throw new FailedLoginException("Passwords do not match.");
		}

		return createHandlerResult(credential, new SimplePrincipal(username), null);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.sql = "SELECT " + this.fieldPassword + " FROM " + this.tableUsers + " WHERE " + this.fieldUser + " = ?";
	}

	/**
	 * @param fieldPassword The fieldPassword to set.
	 */
	public final void setFieldPassword(final String fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	/**
	 * @param fieldUser The fieldUser to set.
	 */
	public final void setFieldUser(final String fieldUser) {
		this.fieldUser = fieldUser;
	}

	/**
	 * @param tableUsers The tableUsers to set.
	 */
	public final void setTableUsers(final String tableUsers) {
		this.tableUsers = tableUsers;
	}

}
