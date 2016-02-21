<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.Tuotteet,beans.Tuote" %>
<%@ page import="db.TuoteDAO" %>
<%
	String[] data = request.getParameterValues("data");
	if (data != null){
		TuoteDAO.init();
		Tuote lisattava = new Tuote();
		
		boolean suoritettu = false;
		
		if (!data[1].equals(""))
			lisattava.setNimi(data[1]);
		if (!data[2].equals(""))
			lisattava.setKoodi(data[2]);
		try {
	        lisattava.setHinta(Double.parseDouble(data[3]));
      	} catch (NumberFormatException ex) {}
		
		if (data[0].equals("")) {
			suoritettu = TuoteDAO.lisaaTuote(lisattava);
		}
		else {
			suoritettu = TuoteDAO.paivitaTuote(TuoteDAO.Column.TUOTEID, 
						data[0], 
						lisattava);
		}
		
		if (suoritettu) {
			pageContext.setAttribute("paivitetty", 
					lisattava);
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Etäharjoitus 7</title>
</head>
<body>
	<h2>Tuotteiden muokkaus</h2>
	<p>Jätä ID tyhjäksi mikäli haluat lisätä uuden tuotteen. 
	Täytä vain ne kentät, joiden arvoa tahdot muuttaa.</p>
	<form>
		TuoteID: <input type="text" name="data"><br>
		Nimi: <input type="text" name="data"><br>
		Koodi: <input type="text" name="data"><br>
		Hinta: <input type="text" name="data"><br>
		<input type="submit" value="Lähetä tiedot">
	</form>
	
	<c:if test="${ not empty paivitetty }">
		<h2>Tuotteen tiedot päivitetty / lisätty!</h2>
    </c:if>
</body>
</html>