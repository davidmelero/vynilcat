<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<br/>

<section id="userInfo">
	<h1><c:out value="${user.userName}" /></h1>
	
	<hr/>
	
	<b>Nombre: </b>
	<c:out value="${user.name}" /><br/>
	<b>Apellidos: </b>
	<c:out value="${user.surname}" /><br/>
	<b>Email: </b>
	<c:out value="${user.email}" /><br/>
	<b>Fecha de registro: </b>
	<c:out value="${user.registrado}" /><br/>
	<b>Tipo de cuenta: </b>
	<c:out value="${user.cuenta}" /> - <span class="nota"><a href='<c:url value="/profile/${user.userName}/upgrade"/>'>Mejorar</a></span><br/><br/>
	
	<hr/>
	
	<h3>Favoritos: </h3>
	<c:forEach var="favs" items="${user.favoritos}">
		<c:out value="${favs.nombreAlbum}" /><br/>
	</c:forEach>
	
	<hr/>
	
	<f:form servletRelativeAction="/logout" method="POST">
		<input type="submit" id="logout" value="Cerrar sesión">
	</f:form>
	
</section>
