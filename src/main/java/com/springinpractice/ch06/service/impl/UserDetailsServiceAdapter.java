/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.service.impl;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch06.dao.UserDetailsDao;
import com.springinpractice.ch06.domain.Account;
import com.springinpractice.ch06.domain.UserDetailsAdapter;
import com.springinpractice.ch06.service.AccountService;

/**
 * Adapts the <code>AccountService</code> and <code>UserDetailsDao</code> to the <code>UserDetailsService</code>
 * interface so Spring Security can use them as an authentication source.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceAdapter implements UserDetailsService {
	@Inject AccountService accountService;
	@Inject UserDetailsDao userDetailsDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		Account account = accountService.getAccountByUsername(username);
		
		// The UserDetailsService contract requires this.
		if (account == null) {
			throw new UsernameNotFoundException("No such user: " + username);
		} else if (account.getRoles().isEmpty()) {
			throw new UsernameNotFoundException("User " + username + " has no authorities");
		}
		
		UserDetailsAdapter user = new UserDetailsAdapter(account);
		user.setPassword(userDetailsDao.findPasswordByUsername(username));
		return user;
	}
}
