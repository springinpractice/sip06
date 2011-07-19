package com.springinpractice.ch06.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Object mother for domain objects.
 */
public final class DomainMom {
	private static final DomainMom MOM = new DomainMom();
	
	public static DomainMom instance() { return MOM; }
	
	private DomainMom() { }
	
	public Account getAccount() {
		Account account = new Account();
		account.setUsername("willie");
		account.setFirstName("Willie");
		account.setLastName("Wheeler");
		account.setEmail("willie@example.com");
		account.setEnabled(true);
		
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setName("user");
		roles.add(role);
		account.setRoles(roles);
		
		return account;
	}
	
	public Account getAccountWithNoRoles() {
		Account account = new Account();
		account.setUsername("user_with_no_roles");
		account.setFirstName("Ima");
		account.setLastName("Userwithnoroles");
		return account;
	}
}
