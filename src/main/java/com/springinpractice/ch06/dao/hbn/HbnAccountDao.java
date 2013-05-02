/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.dao.hbn;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.springinpractice.ch06.dao.AccountDao;
import com.springinpractice.ch06.domain.Account;
import com.springinpractice.ch06.domain.UserDetailsAdapter;
import com.springinpractice.dao.hibernate.AbstractHbnDao;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Repository
public class HbnAccountDao extends AbstractHbnDao<Account> implements AccountDao {
	private static final Logger log = LoggerFactory.getLogger(HbnAccountDao.class);
	
	private static final String UPDATE_PASSWORD_SQL =
		"update account set password = ? where username = ?";
	
	@Inject private JdbcTemplate jdbcTemplate;
	@Inject private PasswordEncoder passwordEncoder;
	@Inject private SaltSource saltSource;
	
	public void create(Account account, String password) {
		log.debug("Creating account: {}", account);
		create(account);
		
		log.debug("Updating password");
		Object salt = saltSource.getSalt(new UserDetailsAdapter(account));
		String encPassword = passwordEncoder.encodePassword(password, salt);
		jdbcTemplate.update(UPDATE_PASSWORD_SQL, encPassword, account.getUsername());
	}

	public Account findByUsername(String username) {
		return (Account) getSession()
				.getNamedQuery("findAccountByUsername")
				.setParameter("username", username)
				.uniqueResult();
	}
}
