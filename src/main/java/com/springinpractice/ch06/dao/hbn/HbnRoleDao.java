package com.springinpractice.ch06.dao.hbn;

import org.springframework.stereotype.Repository;

import com.springinpractice.ch06.dao.RoleDao;
import com.springinpractice.ch06.domain.Role;
import com.springinpractice.dao.hibernate.AbstractHbnDao;

/**
 * @version $Id: HbnRoleDao.java 86 2010-09-06 19:45:05Z  $
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Repository
public class HbnRoleDao extends AbstractHbnDao<Role> implements RoleDao {

	public Role findByName(String name) {
		return (Role) getSession()
			.getNamedQuery("findRoleByName")
			.setParameter("name", name)
			.uniqueResult();
	}
}
