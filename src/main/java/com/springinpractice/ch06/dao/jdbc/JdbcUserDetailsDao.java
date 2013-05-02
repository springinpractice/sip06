/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.dao.jdbc;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springinpractice.ch06.dao.UserDetailsDao;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
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
