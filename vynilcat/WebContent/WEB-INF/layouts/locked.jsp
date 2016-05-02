<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<div id="login" class="loginContainers login">
	<span class="title">Iniciar sesión: </span>
	<br/><br/>
	<span>El usuario <c:out value="${username}" /> ha superado el máximo de intentos. <br/>Por favor, contacte con <a href="mailto:support@catalogovinilos.com">support@catalogovinilos.com</a> </span>
</div>

<div id="register" class="loginContainers register">
	<a href='<c:url value="/register"/>'><img src="./resources/images/icons/register.png" alt="Registrarse" class="button"></a>
<!-- 	<input type="button" id="register" name="register" onclick='location.href=' value="Registrarse" > -->
</div>
