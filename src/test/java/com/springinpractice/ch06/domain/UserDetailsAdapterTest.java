package com.springinpractice.ch06.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

public class UserDetailsAdapterTest {
	private static final DomainMom MOM = DomainMom.instance();
	
	private Account account;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.account = MOM.getAccount();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.account = null;
	}
	
	@Test
	public void testDelegation() {
		UserDetailsAdapter adapter = new UserDetailsAdapter(account);
		adapter.setPassword("password");
		
		assertSame(account, adapter.getAccount());
		assertEquals("willie", adapter.getUsername());
		assertEquals("Willie", adapter.getFirstName());
		assertEquals("Wheeler", adapter.getLastName());
		assertEquals("Willie Wheeler", adapter.getFullName());
		assertEquals("willie@example.com", adapter.getEmail());
		assertEquals("password", adapter.getPassword());
		assertTrue(adapter.isAccountNonExpired());
		assertTrue(adapter.isAccountNonLocked());
		assertTrue(adapter.isCredentialsNonExpired());
		assertTrue(adapter.isEnabled());
		
		Collection<GrantedAuthority> authorities = adapter.getAuthorities();
		assertEquals(1, authorities.size());
	}

}
