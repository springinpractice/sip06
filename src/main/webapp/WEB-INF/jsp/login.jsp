<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="postLoginUrl" value="/j_spring_security_check" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Please log in</title>
	</head>
	<body>
		<h1>Please log in</h1>
		
		<form class="main" action="${postLoginUrl}" method="post">
			<c:if test="${param.failed == true}">
				<div class="warning alert">
					Your login attempt failed. Please try again, or contact technical support for further assistance.
				</div>
			</c:if>
			<div class="panel grid" style="width:420px">
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">Username:</div>
					<div class="yui-u"><input type="text" name="j_username" class="short" /></div>
				</div>
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">Password:</div>
					<div class="yui-u"><input type="password" name="j_password" class="short" /></div>
				</div>
				<div class="gridRow yui-gf">
					<div class="yui-u first"></div>
					<div class="yui-u"><input type="checkbox" name="_spring_security_remember_me" /> Remember me</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="yui-u first"></div>
					<div class="yui-u"><input type="submit" value="Log in" /></div>
				</div>
			</div>
		</form>
	</body>
</html>
