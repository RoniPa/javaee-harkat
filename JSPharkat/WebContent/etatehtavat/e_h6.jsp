<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.Tuotteet,beans.Tuote" %>
<%@ page import="db.TuoteDAO" %>
<%
	String haku = request.getParameter("haku");
	if (haku != null){
		TuoteDAO.init();
		Tuotteet tuotteet = new Tuotteet();
		tuotteet = TuoteDAO.haeTekstilla
				(TuoteDAO.Column.NIMI, request.getParameter("haku"));
		pageContext.setAttribute("tuotteet", tuotteet.getTuotteet());
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Etäharjoitus 6</title>
</head>
<body>
	<form>
		Hakuarvo: <input type="text" name="haku">
		<input type="submit" value="Hae tuotteet">
	</form>
	
	<c:if test="${ not empty tuotteet }">
		<h2>Tulokset:</h2>
		<table>
		<c:forEach items="${ tuotteet }" var="t">
			<tr><td>${ t.nimi }</td><td>${ t.hinta }</td></tr>
		</c:forEach>
	    </table>
    </c:if>
</body>
</html>