<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.Tuotteet,beans.Tuote" %>
<%
	
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Etäharjoitus 3</title>
</head>
<body>
	<jsp:useBean id="kori" class="beans.Tuotteet" scope="session" />
	<jsp:useBean id="tuote" class="beans.Tuote" scope="page"/>
	
	<form method="POST">
        Nimi <input type="text" name="nimi"><br>
        Hinta <input type="text" name="hinta"><br>
        Koodi <input type="text" name="koodi">
        <input type="submit" value="Lisää">
    </form>
    
	<c:if test="${ not empty param.nimi and not empty param.hinta }">
		<c:set target="${ tuote }" property="nimi" value="${ param.nimi }"/>
		<c:set target="${ tuote }" property="hinta" value="${ param.hinta }"/>
		<c:set target="${ tuote }" property="koodi" value="${ param.koodi }"/>
		<% kori.setTuote(tuote); %>
	</c:if>
	
	<h3>Korissa:</h3>
	<c:forEach items="${ kori.getTuotteet() }" var="t">
		<p>${ t.nimi }, ${t.koodi}, hinta ${ t.hinta }</p>
	</c:forEach>
    
</body>
</html>