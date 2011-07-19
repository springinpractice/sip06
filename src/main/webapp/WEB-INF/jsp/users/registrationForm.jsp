<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="submitRegistrationUrl" value="/users.html" />

<spring:message var="pageTitle" code="newUserRegistration.pageTitle" />
<spring:message var="msgAllFieldsRequired" code="newUserRegistration.message.allFieldsRequired" />
<spring:message var="register" code="newUserRegistration.label.register" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><c:out value="${pageTitle}" /></title>
	</head>
	<body>
		<form:form cssClass="main" action="${submitRegistrationUrl}" modelAttribute="account">
			<form:errors path="*">
				<div class="warningBox"><spring:message code="error.global" /></div>
			</form:errors>
			
			<div class="panel">
				<h1><c:out value="${pageTitle}" /></h1>
				
				<p><spring:message code="newUserRegistration.message.allFieldsRequired" /></p>
				
				<div class="formItem yui-gf">
					<div class="yui-u first"><spring:message code="newUserRegistration.label.username" /></div>
					<div class="yui-u">
						<div><form:input path="username" cssClass="short" /></div>
						<form:errors path="username">
							<div class="fieldError"><form:errors path="username" htmlEscape="false" /></div>
						</form:errors>
					</div>
				</div>
				
				<%-- There is a <form:password> tag, but we use straight HTML to suppress form binding --%>
				<div class="formItem yui-gf">
					<div class="yui-u first"><spring:message code="newUserRegistration.label.password" /></div>
					<div class="yui-u">
						<div><input type="password" name="password" class="short" /></div>
						<form:errors path="password">
							<div class="fieldError"><form:errors path="password" htmlEscape="false" /></div>
						</form:errors>
					</div>
				</div>
				<div class="formItem yui-gf">
					<div class="yui-u first"><spring:message code="newUserRegistration.label.confirmPassword" /></div>
					<div class="yui-u"><input type="password" name="confirmPassword" class="short" /></div>
				</div>
	
				<div class="formItem yui-gf">
					<div class="yui-u first"><spring:message code="newUserRegistration.label.firstName" /></div>
					<div class="yui-u">
						<div><form:input path="firstName" cssClass="short" /></div>
						<form:errors path="firstName">
							<div class="fieldError"><form:errors path="firstName" htmlEscape="false" /></div>
						</form:errors>
					</div>
				</div>
				<div class="formItem yui-gf">
					<div class="yui-u first"><spring:message code="newUserRegistration.label.lastName" /></div>
					<div class="yui-u">
						<div><form:input path="lastName" cssClass="short" /></div>
						<form:errors path="lastName">
							<div class="fieldError"><form:errors path="lastName" htmlEscape="false" /></div>
						</form:errors>
					</div>
				</div>
				<div class="formItem yui-gf">
					<div class="yui-u first"><spring:message code="newUserRegistration.label.email" /></div>
					<div class="yui-u">
						<div><form:input path="email" cssClass="medium" /></div>
						<form:errors path="email">
							<div class="fieldError"><form:errors path="email" htmlEscape="false" /></div>
						</form:errors>
					</div>
				</div>
				<div class="formItem">
					<form:checkbox id="marketingOk" path="marketingOk" />
					<label for="marketingOk"><spring:message code="newUserRegistration.label.marketingOk" /></label>
				</div>
				<div class="formItem">
					<div>
						<form:checkbox id="acceptTerms" path="acceptTerms" />
						<label for="acceptTerms"><spring:message code="newUserRegistration.label.acceptTerms" /></label>
					</div>
					<form:errors path="acceptTerms">
						<div class="fieldError"><form:errors path="acceptTerms" htmlEscape="false" /></div>
					</form:errors>
				</div>
				<div class="formItem">
					<spring:message code="newUserRegistration.label.privacyPolicy" />
				</div>
				<div class="formItem submit"><input type="submit" value="${register}"></input></div>
			</div>
		</form:form>
	</body>
</html>
