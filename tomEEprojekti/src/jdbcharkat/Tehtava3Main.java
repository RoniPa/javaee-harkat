package jdbcharkat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Tehtava3Main {
	public static void main(String args[]){
		Connection conn = TietokantayhteysTehdas.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try {
			stmt = conn.prepareStatement("SELECT * FROM henkilo WHERE sukunimi LIKE ?");
			
			// Tehdään haut
			for (int i = 0; i < 4; i++){
				String haku;
				String tuloste;
				int colCount = 0;
				
				switch(i){
					case 0: haku = "Ank%"; 
						break;
					case 1: haku = "%iri"; 
						break;
					case 2: haku = "%kaulus%"; 
						break;
					case 3: haku = "Vek%"; 
						break;
					default:haku = "";
				}
				
				stmt.setString(1, haku);
				rs = stmt.executeQuery();
				rsmd = rs.getMetaData();
				colCount = rsmd.getColumnCount();
				
				tuloste = "************sukunimi "+ haku +"************\n";
				while(rs.next()){
					for (int j = 1; j <= colCount; j++){
						tuloste += rs.getString(j) + "\t"; 
					}
					tuloste += "\n";
				}
				
				System.out.println(tuloste);
			}
		}
		catch (SQLException ex){
			System.err.println("Ei saanut PreparedStatementtia luotua");
		}
		
		TietokantaApu.close(conn);
		TietokantaApu.close(stmt);
		TietokantaApu.close(rs);
	}
}
