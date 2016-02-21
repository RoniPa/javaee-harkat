<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.Tuotteet,beans.Tuote" %>
<%@ page import="db.TuoteDAO" %>
<%
	TuoteDAO.init();
	Tuotteet tuotteet = new Tuotteet();
	tuotteet = TuoteDAO.haeJarjestyksessa
			(TuoteDAO.Column.HINTA, TuoteDAO.OrderBy.DESC);
	pageContext.setAttribute("tuotteet", tuotteet.getTuotteet());
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Etäharjoitus 5</title>
</head>
<body>	
	<h2>Tuotehaku:</h2>
	<table>
	<c:forEach items="${ tuotteet }" var="t">
		<tr><td>${ t.nimi }</td><td>${ t.hinta }</td></tr>
	</c:forEach>
    </table>
</body>
</html>