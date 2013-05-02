/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class UserDetailsAdapter implements UserDetails {
	private Account account;
	private String password;
	
	public UserDetailsAdapter(Account account) { this.account = account; }
	
	public Account getAccount() { return account; }
	
	public Long getId() { return account.getId(); }
	
	public String getFirstName() { return account.getFirstName(); }
	
	public String getLastName() { return account.getLastName(); }
	
	public String getFullName() { return account.getFullName(); }
	
	public String getEmail() { return account.getEmail(); }

	@Override
	public String getUsername() { return account.getUsername(); }
	
	@Override
	public String getPassword() { return password; }
	
	// Provide a setter since the underlying Account doesn't support passwords
	public void setPassword(String password) { this.password = password; }
	
	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return account.isEnabled(); }

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : account.getRoles()) {
			authorities.add(new GrantedAuthorityImpl(role.getName()));
		}
		return authorities;
	}
}
