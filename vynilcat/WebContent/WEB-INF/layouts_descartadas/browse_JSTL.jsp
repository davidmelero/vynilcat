<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<br/>

<div id="errorContainer" class="error">
	<img id="broken" src='<c:url value="./resources/images/Broken-vinyl.png" />' /><br/>
	<span></span>
</div>

<section id="search" class="bgsearch contentSection style-1 clearfix">
	<img alt="Catálogo de Vinilos" src="resources/images/logo.png">
	<f:form id="searchForm" name="searchForm" servletRelativeAction="/search/all.json">
		<input type="text" id="search" name="search" size="80" >
		<input type="submit" id="searchBtn" value="Buscar">
	</f:form>
</section>

<section id="results" class="contentSection">
	<div id="searched" class="nota">
		'<c:out value="${searched}"/>'
	</div><br/>
	<div>
		<span class="title">Albumes: </span><br/><br/>
		<c:if test="${empty albumList}">
			<span>No hay coincidencias :( </span>
		</c:if>
		
		<c:forEach items="${albumList}" var="al">
			<c:choose>
				<c:when test="${fn:length(al.artista)>1}">
					<span>Varios - <c:out value="${al.nombre}" /></span><br/>
				</c:when>
				<c:when test="${fn:length(al.artista)==1}">
					<span><c:out value="${al.artista.get(0).nombre}"></c:out> - <c:out value="${al.nombre}" /></span><br/>
				</c:when>
				<c:otherwise>
					<span><c:out value="${al.nombre}" /></span><br/>
				</c:otherwise>
			</c:choose>
			 
		</c:forEach>
		
	</div>
	<div>	
		<span class="title">Artistas: </span><br/><br/>
		<c:if test="${empty artistaList}">
			<span>No hay coincidencias :( </span>
		</c:if>
		
		<c:forEach items="${artistaList}" var="ar">
			<c:out value="${ar.nombre}" /> <br/>
		</c:forEach>
		
	</div>
	<div>
		<span class="title">Sellos discográficos: </span><br/><br/>
		<c:if test="${empty selloList}">
			<span>No hay coincidencias :( </span>
		</c:if>
		
		<c:forEach items="${selloList}" var="s">
			<c:out value="${s.sello}" /> <br/>
		</c:forEach>
		
	</div>
</section>
