<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<br/>

<input type="hidden" id="root" value='<c:url value="/admin"/>' />
<%-- <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>

<section id="adminConsole">	
	<h3>1 - Usuarios</h3>
	Listado de usuarios: <br/><br/>
	<f:form id="userForm" method="POST">
		<c:forEach var="user" items="${loginUsuarioList}" >
			<c:set var="isEnabled" value="${user.enabled}" />
			<c:out value="${user.userName}"/>
			 -  
			<c:if test="${isEnabled}">
				<span class="nota green">Activo</span> - <a href='<c:url value="/admin/ban/${user.idUsuario}"/>'>Banear</a>
			</c:if>
			<c:if test="${!isEnabled}">
				<span class="nota red">Inactivo</span> - <a href='<c:url value="/admin/ban/${user.idUsuario}"/>'>Activar</a> 	
			</c:if>
			<br/>
		</c:forEach>
	</f:form>
	
	<hr/>

</section>
