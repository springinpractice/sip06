<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="loginUrl" value="/login.html" />

<spring:message var="pageTitle" code="registrationOk.pageTitle" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><c:out value="${pageTitle}" /></title>
	</head>
	<body>
		<h1><c:out value="${pageTitle}" /></h1>
		<p><spring:message code="registrationOk.message.thanks" /></p>
		<p><a href="${loginUrl}">Log in</a></p>
	</body>
</html>
