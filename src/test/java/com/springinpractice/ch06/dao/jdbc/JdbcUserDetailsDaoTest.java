package com.springinpractice.ch06.dao.jdbc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUserDetailsDaoTest {
	private static final String USERNAME = "willie";
	private static final String PASSWORD = "password";
	
	@InjectMocks private JdbcUserDetailsDao dao;
	@Mock private JdbcTemplate template;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.dao = new JdbcUserDetailsDao();
		MockitoAnnotations.initMocks(this);
		
		when(template.queryForObject(
			"select password from account where username = ?",
			new Object[] { USERNAME },
			String.class)).thenReturn(PASSWORD);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.dao = null;
		this.template = null;
	}
	
	@Test
	public void testGetPassword() {
		String password = dao.findPasswordByUsername(USERNAME);
		assertEquals(PASSWORD, password);
	}
}
