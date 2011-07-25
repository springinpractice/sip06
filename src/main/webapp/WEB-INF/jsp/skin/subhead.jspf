<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="homeUrl" value="/home.html" />
<c:url var="loginUrl" value="/spring_security_login" />
<c:url var="logoutUrl" value="/j_spring_security_logout" />

<div id="subhd" class="yui-g">
	<div class="yui-u first">
		<div id="topNav">
			<ul>
				<li><a href="${homeUrl}">Home</a></li>
			</ul>
		</div>
	</div>
	<div class="yui-u">
		<div id="sessionInfo">
			<security:authorize access="isAnonymous()">
				Hi, guest. <a href="${loginUrl}">Log in</a>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				Hi, <security:authentication property="principal.username" />.
				<a href="${logoutUrl}">Log out</a>
			</security:authorize>
		</div>
	</div>
</div>
