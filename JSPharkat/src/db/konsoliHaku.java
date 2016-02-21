package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class konsoliHaku {
	public static void main(String args[]){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = null;
		
		do {
			System.out.println("Haetaanko tiedot tuotetaulu-taulusta? (y/n)");
			try{
		        in = br.readLine();
	        }catch(Exception nfe){
	            System.err.println("Voi voi voi!");
	        }
		
			if (in.equalsIgnoreCase("y")){
				Connection c = TietokantayhteysTehdas.getConnection();
				try {
					Statement stmt = c.createStatement();
					ResultSet tulokset = stmt.executeQuery("SELECT * FROM tuotetaulu");
					
					while(tulokset.next()){
						String r = "";
						
						r += tulokset.getString("nimi") + ", ";
						r += tulokset.getString("koodi") + ", ";
						r += tulokset.getString("hinta");
						
						System.out.println(r);
					}
				} catch (SQLException ex){
					System.err.println("SQL poikkeus on tapahtunut");
				} finally {
					TietokantaApu.close(c);
				}
			}
		} while (in != null && in.equalsIgnoreCase("n"));
	}
}
