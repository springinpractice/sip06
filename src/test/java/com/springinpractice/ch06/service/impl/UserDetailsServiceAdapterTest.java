/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springinpractice.ch06.dao.UserDetailsDao;
import com.springinpractice.ch06.domain.DomainMom;
import com.springinpractice.ch06.service.AccountService;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class UserDetailsServiceAdapterTest {
	private static final DomainMom MOM = DomainMom.instance();
	
	@InjectMocks private UserDetailsServiceAdapter adapter;
	@Mock private AccountService accountService;
	@Mock private UserDetailsDao userDetailsDao;

	@Before
	public void setUp() throws Exception {
		this.adapter = new UserDetailsServiceAdapter();
		MockitoAnnotations.initMocks(this);
		
		when(accountService.getAccountByUsername("willie")).thenReturn(MOM.getAccount());
		when(accountService.getAccountByUsername("user_with_no_roles")).thenReturn(MOM.getAccountWithNoRoles());
		when(userDetailsDao.findPasswordByUsername("willie")).thenReturn("password");
	}

	@After
	public void tearDown() throws Exception {
		this.adapter = null;
		this.accountService = null;
		this.userDetailsDao = null;
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void testNonExistentUserThrowsUsernameNotFoundException() {
		adapter.loadUserByUsername("i_dont_exist");
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void testUserWithNoRolesThrowsUsernameNotFoundException() {
		adapter.loadUserByUsername("user_with_no_roles");
	}
	
	@Test
	public void testLoadUserByUsername() {
		UserDetails user = adapter.loadUserByUsername("willie");
		assertEquals("willie", user.getUsername());
		assertEquals("password", user.getPassword());
	}
}
