/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NamedQuery(
	name = "findAccountByUsername",
	query = "from Account where username = :username")
@Entity
@Table(name = "account")
public class Account {
	private Long id;
	private String username, firstName, lastName, email;
	private boolean marketingOk = true, acceptTerms = false, enabled = true;
	private Date dateCreated;
	private Collection<Role> roles = new HashSet<Role>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() { return id; }
	
	@SuppressWarnings("unused")
	private void setId(Long id) { this.id = id; }
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "username")
	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "first_name")
	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "last_name")
	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }
	
	@Transient
	public String getFullName() { return firstName + " " + lastName; }
	
	@NotNull
	@Size(min = 6, max = 50)
	@Email
	@Column(name = "email")
	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }
	
	@Column(name = "marketing_ok")
	public boolean isMarketingOk() { return marketingOk; }
	
	public void setMarketingOk(boolean marketingOk) { this.marketingOk = marketingOk; }
	
	@AssertTrue(message = "{account.acceptTerms.assertTrue.message}")
	@Column(name = "accept_terms")
	public boolean getAcceptTerms() { return acceptTerms; }
	
	public void setAcceptTerms(boolean acceptTerms) { this.acceptTerms = acceptTerms; }
	
	@Column(name = "enabled")
	public boolean isEnabled() { return enabled; }

	public void setEnabled(boolean enabled) { this.enabled = enabled; }
	
	@ManyToMany
	@JoinTable(
		name = "account_role",
		joinColumns = { @JoinColumn(name = "account_id", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	public Collection<Role> getRoles() { return roles; }
	
	public void setRoles(Collection<Role> roles) { this.roles = roles; }
	
	@Column(name = "date_created")
	public Date getDateCreated() { return dateCreated; }
	
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
}
