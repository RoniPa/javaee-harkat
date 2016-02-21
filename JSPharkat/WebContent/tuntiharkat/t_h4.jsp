<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Tuntiharj 4</title>
</head>
<body>
	<form method="post">
		Etunimi <input type="text" name = "etunimi" placeholder="anna etunimi"><br>
		Sukunimi <input type="text" name="sukunimi" placeholder="anna sukunimi"><br>
		<input type="submit" value="Lähetä">
	</form>
	
	<c:if test="${ not empty param.etunimi and not empty param.sukunimi }">
		<p>Etunimesi on ${ param.etunimi } ja sukunimesi on ${ param.sukunimi }</p>
	</c:if>
</body>
</html>