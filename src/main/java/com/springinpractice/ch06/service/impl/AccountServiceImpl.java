/* 
 * $Id: AccountServiceImpl.java 86 2010-09-06 19:45:05Z  $
 * 
 * Copyright (c) 2010 Manning Publications Co.
 * 
 * Book web site   - http://www.manning.com/wheeler/
 * Book blog       - http://springinpractice.com/
 * Author web site - http://wheelersoftware.com/
 */
package com.springinpractice.ch06.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.springinpractice.ch06.dao.AccountDao;
import com.springinpractice.ch06.dao.RoleDao;
import com.springinpractice.ch06.domain.Account;
import com.springinpractice.ch06.domain.Role;
import com.springinpractice.ch06.service.AccountService;

/**
 * @version $Id: AccountServiceImpl.java 86 2010-09-06 19:45:05Z  $
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Inject private AccountDao accountDao;
	@Inject private RoleDao roleDao;
	
	@Transactional(readOnly = false)	
	public boolean registerAccount(Account account, String password, Errors errors) {
		validateUsername(account.getUsername(), errors);
		boolean valid = !errors.hasErrors();
		
		if (valid) {
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleDao.findByName("user"));
			account.setRoles(roles);
			accountDao.create(account, password);
		}
		
		return valid;
	}
	
	private void validateUsername(String username, Errors errors) {
		if (accountDao.findByUsername(username) != null) {
			log.debug("Validation failed: duplicate username");
			errors.rejectValue("username", "error.duplicate", new String[] { username }, null);
		}
	}
	
	// For recipe 6.6
	public Account getAccountByUsername(String username) {
		Account account = accountDao.findByUsername(username);
		if (account != null) { Hibernate.initialize(account.getRoles()); }
		return account;
	}
}
