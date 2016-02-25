package servletit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Tuote;
import beans.Tuotteet;
import db.TuoteDAO;

@WebServlet("/Tuotteet")
public class TietokantaServletti extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Tuotteet tuotteet;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TietokantaServletti() {
        super();
        TuoteDAO d = TuoteDAO.getInstance();
        try {
        	this.tuotteet = d.haeKaikki();
        } catch (SQLException ex){
        	System.err.println("Tietoja ei saatu haettua");
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String output = "<h2>Tuotteet:</h2><pre>";
		
		Iterator<Tuote> i = this.tuotteet.getTuotteet().iterator();
		while(i.hasNext()){
			Tuote t = i.next();
			output += t.toString() + "\n";
		}
		
		output += "</pre>";
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
