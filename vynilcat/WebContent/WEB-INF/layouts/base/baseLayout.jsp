<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
 
<!Doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Catálogo Vinilos</title>
	
	<link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>'/>
	
	<script type="text/javascript" src='<c:url value="/scripts/jquery.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/scripts/main.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/scripts/jQueryRotate.js"/>'></script>
	
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>

<body>
	<div id="container">
		<header id="header"><t:insertAttribute name="header"/></header>
		<section id="content"><t:insertAttribute name="content"/></section>
	</div>
	<footer id="footer"><t:insertAttribute name="footer"></t:insertAttribute></footer>
</body>
</html>