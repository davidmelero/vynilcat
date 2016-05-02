<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
 
<s:url var="es" value="/" scope="session" >
	<s:param name="lang" value="es" />
</s:url>

<s:url var="en" value="/" scope="session" >
	<s:param name="lang" value="en" />
</s:url>
 
<div class="left">
	<span><a href='<c:url value="/"/>'>
		<img class="logo" alt="VynilCat" src='<c:url value="/images/logo_cat.png" />'> <span class="orange">VynilCat</span> - <s:message code="vinilos.titulo"/>
	</a></span>
	&nbsp;&nbsp;
	<a href="${es}"><img class="headerIcons" src='<s:url value="/images/icons/flags/es.png"/>' id="es"></a>
	<a href="${en}"><img class="headerIcons" src='<s:url value="/images/icons/flags/en.png"/>' id="en"></a>
</div>

<div class="right">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.username" var="username" scope="session"/>
		<span>		
			<a href='<c:url value="/profile/${username}"/>'>
				<img class="headerIcons" alt="Área de usuario" src='<c:url value="/images/icons/user.png" />'>
				<c:out value="${username}" />
			</a>
		</span>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href='<c:url value="/admin"/>'>
			<img class="headerIcons" alt="Panel de control" src='<c:url value="/images/icons/config.png" />'>
		</a>
	</sec:authorize>
</div>