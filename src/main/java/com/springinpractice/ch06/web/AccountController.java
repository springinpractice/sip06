/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch06.web;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springinpractice.ch06.domain.Account;
import com.springinpractice.ch06.service.AccountService;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/users")
public class AccountController {
	private static final String VN_REG_FORM = "users/registrationForm";
	private static final String VN_REG_OK = "redirect:/users/registration_ok.html";
	
	@Inject private AccountService accountService;
	
	@Inject
	@Qualifier("authenticationManager")
	private AuthenticationManager authMgr;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { 
			"username", "password", "confirmPassword", "firstName", "lastName",
			"email", "marketingOk", "acceptTerms"
		});
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		model.addAttribute("account", new AccountForm());
		return VN_REG_FORM;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postRegistrationForm(
			@ModelAttribute("account") @Valid AccountForm form,
			BindingResult result) {
		
		convertPasswordError(result);
		String password = form.getPassword();
		accountService.registerAccount(toAccount(form), password, result);
		
		Authentication authRequest =
			new UsernamePasswordAuthenticationToken(form.getUsername(), password);
		Authentication authResult = authMgr.authenticate(authRequest);
		SecurityContextHolder.getContext().setAuthentication(authResult);
		
		return (result.hasErrors() ? VN_REG_FORM : VN_REG_OK);
	}

	private static void convertPasswordError(BindingResult result) {
		// Map class-level Hibernate error message to field-level Spring error message.
		for (ObjectError error : result.getGlobalErrors()) {
			String msg = error.getDefaultMessage();
			if ("account.password.mismatch.message".equals(msg)) {
				// Don't show if there's already some other error message.
				if (!result.hasFieldErrors("password")) {
					result.rejectValue("password", "error.mismatch");
				}
			}
		}
	}
	
	private static Account toAccount(AccountForm form) {
		Account account = new Account();
		account.setUsername(form.getUsername());
		account.setFirstName(form.getFirstName());
		account.setLastName(form.getLastName());
		account.setEmail(form.getEmail());
		account.setMarketingOk(form.isMarketingOk());
		account.setAcceptTerms(form.getAcceptTerms());
		account.setEnabled(true);
		return account;
	}
}
