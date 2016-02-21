<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Tuntiharj 2</title>
</head>
<body>
	<%	  
       java.util.ArrayList<String> kielet = new java.util.ArrayList<String>();
       kielet.add("Java");
       kielet.add("C");
       kielet.add("C#");
       kielet.add("php");
            
       java.util.HashMap<String, Integer> vuodet = new java.util.HashMap<String, Integer>();
       vuodet.put("Java", 1995);
       vuodet.put("C", 1971);
       vuodet.put("C#", 2000);
       vuodet.put("PHP", 1995);        
       vuodet.put("Scala", 2003);
       vuodet.put("C++", 1983);
       
       session.setAttribute("kielet", kielet);
       session.setAttribute("vuodet", vuodet);
    %>
    <ul><h3>KIELET:</h3>
    <c:forEach var="kieli" items="${ kielet }">
    	<li><c:out value="${ kieli }"/></li>
    </c:forEach>
	</ul>
	
	<ul><h3>VUODET:</h3>
    <c:forEach var="vuosi" items="${ vuodet }">
    	<li><c:out value="${ vuosi.key }, ${ vuosi.value }"/></li>
    </c:forEach>
	</ul>
</body>
</html>