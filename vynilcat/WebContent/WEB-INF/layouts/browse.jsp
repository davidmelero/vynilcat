<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<br/>

<div id="errorContainer" class="error">
	<img id="broken" src='<c:url value="/images/Broken-vinyl.png" />' /><br/>
	<span></span>
</div>

<section id="search" class="bgsearch contentSection style-1 clearfix">
	<img id="logo" alt="Catálogo de Vinilos" src="<c:url value='/images/logo.png'/>" />
	<f:form id="searchForm" name="searchForm" servletRelativeAction="/search/all.json" commandName="search">
		<f:input path="searched" id="searched" name="searched" size="80" />
		<input type="submit" id="searchBtn" value="Buscar">
	</f:form>
</section>

<section id="results" class="contentSection">
	<div id="searched" class="nota"></div>
	<br/>
	
	<div id="albumes">
		<span class="title">Albumes: </span>
		<br/><br/>
		<div id="albumesContent"></div>
	</div>
	
	<div id="artistas">	
		<span class="title">Artistas: </span>
		<br/><br/>
		<div id="artistasContent"></div>
	</div>
	
	<div id="sellos">
		<span class="title">Sellos discográficos: </span>
		<br/><br/>
		<div id="sellosContent"></div>
	</div>
</section>
