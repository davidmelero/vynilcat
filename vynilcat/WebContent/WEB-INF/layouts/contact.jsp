<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<div id="register" class="loginContainers newRegister">
	<span class="title">Formulario de contacto: </span>
	<br/><br/>
	<sf:form method="POST">
		<br/>
		<table>
			<tr>
				<td>Título: </td>
				<td>
					<input type="text" id="title" name="title" size="50" value="Se me ha roto esto!"/>
					<sf:errors path="title" cssClass="errorInForm"/>
				</td>
			</tr>
			<tr>
				<td>Mensaje: </td>
				<td>
					<textarea id="body" name="body" rows="5" cols="38"></textarea>
					<sf:errors path="body" cssClass="errorInForm"/>
				</td>
			</tr>	
			<tr>
				<td colspan="2">
					<input type="submit" id="send" name="send" value=" Enviar "/>
				</td>
			</tr>
		</table>		
	</sf:form>
</div>