<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ include file="/WEB-INF/jsp/init.jsp" %>

<c:set var="pageTitle" value="Please log in" />
<c:url var="postLoginUrl" value="/j_spring_security_check" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${pageTitle}</title>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/topNav.jspf" %>
		
		<c:if test="${param.failed == true}">
			<div class="warningBox">
				Your login attempt failed. Please try again, or contact technical support for further assistance.
			</div>
		</c:if>
		
		<h1>${pageTitle}</h1>
		
		<form class="main" action="${postLoginUrl}" method="post">
			<div class="formItem yui-gf">
				<div class="yui-u first">Username:</div>
				<div class="yui-u"><input type="text" name="j_username" class="short" /></div>
			</div>
			<div class="formItem yui-gf">
				<div class="yui-u first">Password:</div>
				<div class="yui-u"><input type="password" name="j_password" class="short" /></div>
			</div>
			<div class="formItem yui-gf">
				<div class="yui-u first"></div>
				<div class="yui-u"><input type="checkbox" name="_spring_security_remember_me" /> Remember me</div>
			</div>
			<div class="formItem yui-gf">
				<div class="yui-u first"></div>
				<div class="yui-u"><input type="submit" value="Log in" /></div>
			</div>
		</form>
	</body>
</html>
