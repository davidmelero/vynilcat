<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<div id="login" class="loginContainers login">
	<span class="title">Iniciar sesión: </span>
	<f:form action="${loginProcessingUrl}" method="POST">
		<table>
			<tr>
				<td>Nombre de usuario: </td>
				<td><input type="text" id="user" name="user" value="Administrador"/></td>
			</tr>
			<tr>
				<td>Contraseña: </td>
				<td><input type="password" id="pass" name="pass" value="12345" /></td>
			</tr>
			<tr>
				<td colspan=2>Recuerdame: <input type="checkbox" name="remember_me" >	</td>
			</tr>
			<tr>
				<td colspan=2><input type="submit" id="entrar" name="entrar" value="Iniciar sesión"></td>
			</tr>	
		</table>		
	</f:form>
</div>

<div id="register" class="loginContainers register">
	<a href='<c:url value="/register"/>'><img src="./resources/images/icons/register.png" alt="Registrarse" class="button"></a>
<!-- 	<input type="button" id="register" name="register" onclick='location.href=' value="Registrarse" > -->
</div>
