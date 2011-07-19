package com.springinpractice.ch06.dao.jdbc;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springinpractice.ch06.dao.UserDetailsDao;

@Repository
public class JdbcUserDetailsDao implements UserDetailsDao {
	@Inject private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_PASSWORD_SQL =
		"select password from account where username = ?";

	@Override
	public String findPasswordByUsername(String username) {
		return jdbcTemplate.queryForObject(
			FIND_PASSWORD_SQL, new Object[] { username }, String.class);
	}
}
