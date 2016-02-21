<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.Enumeration" %>
    
<!DOCTYPE html>
<html>
<head>

<%
	pageContext.setAttribute("header", request.getHeader("user-agent"));
	pageContext.setAttribute("reqUri", request.getRequestURI()); 
	pageContext.setAttribute("remHost", request.getRemoteHost());
	pageContext.setAttribute("remAddr", request.getRemoteAddr());
	pageContext.setAttribute("locAddr", request.getLocalAddr());
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Tuntiharjoitus 3</title>
</head>
<body>
	<p>Header: <br>
	<c:forEach var="elem" items="${ header }">
	${ elem.key } : ${ elem.value }<br>
	</c:forEach></p>
	<p>Request URI: ${ reqUri }</p>
	<p>Remote Host: ${ remHost }</p>
	<p>Remote Address: ${ remAddr }</p>
	<p>Local Address: ${ locAddr }</p>

	<p>Otsikot:<br>
	<%
	Enumeration headerNames = request.getHeaderNames();
	
	while (headerNames.hasMoreElements()) {
		out.println(headerNames.nextElement());
	}
	%>
	</p>
</body>
</html>