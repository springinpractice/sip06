/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.dao;

import com.springinpractice.ch06.domain.Account;
import com.springinpractice.dao.Dao;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface AccountDao extends Dao<Account> {
	
	void create(Account account, String password);
	
	Account findByUsername(String username);
}
