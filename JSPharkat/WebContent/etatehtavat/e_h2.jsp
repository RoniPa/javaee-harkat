<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.Tuotteet,beans.Tuote" %>
<%
	/*
	Tuotteet tuotteet = new Tuotteet();
	tuotteet.setTuote(new Tuote("Microsoft Office 2010 Standard Open License", "021-05429", 736.90));
	tuotteet.setTuote(new Tuote("HP Mini 5103 10.1", "XM592AA#UUW", 398.90));
	tuotteet.setTuote(new Tuote("Adobe Creative Suite 5 Design Premium", "65064508AF01A00", 2273.90));
	tuotteet.setTuote(new Tuote("Adobe Acrobat Pro 7.0", "22020173", 623.90 ));
	tuotteet.setTuote(new Tuote("Sony Bravia KDL-40EX402 40 Full HD", "KDL40EX402", 699.90 ));
	tuotteet.setTuote(new Tuote("Archos 101 Internet Tablet 16 GB","501594", 408.90));
	tuotteet.setTuote(new Tuote("Nokia Booklet 3G", "02717X8", 589.90));
	tuotteet.setTuote(new Tuote("Apple Mac OS X v10.6.3 Snow Leopard", "MC573", 27.90));
	tuotteet.setTuote(new Tuote("F-Secure Internet Security 2010 Fin", "FCI0OE1N001FI", 29.90));
	tuotteet.setTuote(new Tuote("Nokia N900", "002M115", 449.90));
	pageContext.setAttribute("tuotelista", tuotteet);
	*/
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Etäharjoitus 2</title>
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