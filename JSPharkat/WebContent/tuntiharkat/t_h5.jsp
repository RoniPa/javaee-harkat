<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Tuntiharj 5</title>
</head>
<body>
	<jsp:useBean id="kori" class="java.util.ArrayList" scope="session"/>
	<jsp:useBean id="tuote" class="tuntipavut.Tuote" scope="page"/>
	
	<form method="post">
		Nimi <input type="text" name="nimi"><br>
		Hinta <input type="text" name="hinta">
		<input type="submit" value="Luo">
	</form>
	
	<c:if test="${ not empty param.nimi and not empty param.hinta }">
		<c:set target="${ tuote }" property="nimi" value="${ param.nimi }"/>
		<c:set target="${ tuote }" property="hinta" value="${ param.hinta }"/>
		<% kori.add(tuote); %>
	</c:if>
	
	<h3>Korissa:</h3>
	<c:forEach items="${ kori }" var="t">
		<p>${ t.nimi }, hinta ${ t.hinta }</p>
	</c:forEach>
</body>
</html>