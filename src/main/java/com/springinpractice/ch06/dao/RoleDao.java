package com.springinpractice.ch06.dao;

import com.springinpractice.ch06.domain.Role;
import com.springinpractice.dao.Dao;

public interface RoleDao extends Dao<Role> {
	
	Role findByName(String name);
}
