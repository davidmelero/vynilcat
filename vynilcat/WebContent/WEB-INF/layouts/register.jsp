<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<div id="register" class="loginContainers newRegister">
	<span class="title">Nuevo registro: </span>
	<br/><br/>
	<sf:form method="POST" commandName="usuario" >
		<span id="error" class="errorInForm">
			<c:out value="${error}" />
		</span>
		<br/>
		<table>
			<tr>
				<td>Nombre de usuario: </td>
				<td>
					<input type="text" id="userName" name="userName" size="30" value="ManueltorTheReturn"/>
					<sf:errors path="userName" cssClass="errorInForm"/>
				</td>
			</tr>
			<tr>
				<td>Contraseña: </td>
				<td>
					<input type="password" id="password" name="password" size="30" value="1234" />
					<sf:errors path="password" cssClass="errorInForm"/>
				</td>
			</tr>

			<tr>
				<td>Repetir Contraseña: </td>
				<td>
					<input type="password" id="repeatPassword" name="repeatPassword" size="30" value="1234" />
					<sf:errors path="repeatPassword" cssClass="errorInForm"/>	
				</td>
			</tr>

			<tr>
				<td>Email: </td>
				<td>
					<input type="text" id=email name="email" size="30" value="pumpumpumpum@gmail.com"/>
					<sf:errors path="email" cssClass="errorInForm"/>	
				</td>
			</tr>
			<tr>
				<td>Nombre: </td>
				<td>
					<input type="text" id="name" name="name" size="30" value="Manuel"/>
					<sf:errors path="name" cssClass="errorInForm"/>	
				</td>
			</tr>
			<tr>
				<td>Apellidos: </td>
				<td>
					<input type="text" id="surname" name="surname" size="30" value="Torre Iglesias"/>
					<sf:errors path="surname" cssClass="errorInForm"/>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<input type="submit" id="accept" name="accept" value="Aceptar"> 
				<input type="button" id="back" name="back" onclick='<c:url value="/"/>' value="Volver" >
				</td>
			</tr>	
		</table>		
	</sf:form>
</div>