/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.web;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
// Requires scripting engine (e.g. Rhino included automatically with Java 6)
@ScriptAssert(
	lang = "javascript",
	script = "_this.confirmPassword.equals(_this.password)",
	message = "account.password.mismatch.message")
public class AccountForm {
	private String username, password, confirmPassword, firstName, lastName, email;
	private boolean marketingOk = true, acceptTerms = false;
	
	@NotNull
	@Size(min = 1, max = 50)
	public String getUsername() { return username; }

	public void setUsername(String userName) { this.username = userName; }

	@NotNull
	@Size(min = 6, max = 50)
	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public String getConfirmPassword() { return confirmPassword; }

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@NotNull
	@Size(min = 1, max = 50)
	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	@NotNull
	@Size(min = 1, max = 50)
	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }
	
	@NotNull
	@Size(min = 6, max = 50)
	@Email
	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }
	
	public boolean isMarketingOk() { return marketingOk; }
	
	public void setMarketingOk(boolean marketingOk) { this.marketingOk = marketingOk; }
	
	@AssertTrue(message = "{account.acceptTerms.assertTrue.message}")
	public boolean getAcceptTerms() { return acceptTerms; }
	
	public void setAcceptTerms(boolean acceptTerms) { this.acceptTerms = acceptTerms; }
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("username", username)
			.append("firstName", firstName)
			.append("lastName", lastName)
			.append("email", email)
			.append("marketingOk", marketingOk)
			.append("acceptTerms", acceptTerms)
			.toString();			
	}		
}
