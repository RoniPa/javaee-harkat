package jdbcharkat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Tehtava4Main {
	final private static String LISAA = "INSERT INTO henkilo(etunimi,sukunimi,sotu,palkka,syntymaaika) VALUES (?,?,?,?,?)";
	
	public static void main(String args[]){
		Connection conn = TietokantayhteysTehdas.getConnection();
		PreparedStatement lisaaHlo = null;
		ResultSet rs = null;
		
		try {
			conn.setAutoCommit(false); // automaattinen suoritus pois
			lisaaHlo = conn.prepareStatement(LISAA);
			
			asetaArvot("Musta", "Pekka", "010626", 1200.00, "1926-06-01", lisaaHlo);
			asetaArvot("Musta", "Kaapu", "300539", 12900.90, "1939-05-30", lisaaHlo);
			
			conn.commit();
			
			conn.setAutoCommit(true); // takasin päälle
			
			// Lisätyt ulos
			
			PreparedStatement testiHaku = conn.prepareStatement("SELECT * FROM henkilo WHERE etunimi='Musta'");
			rs = testiHaku.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int colCount = rsmd.getColumnCount();
			
			String tuloste = "************lisätyt heput************\n";
			while(rs.next()){
				for (int j = 1; j <= colCount; j++){
					tuloste += rs.getString(j) + "\t"; 
				}
				tuloste += "\n";
			}
			
			System.out.println(tuloste);
			
		} catch (SQLException ex) {				
			System.err.println("TAPAHTUI KAAMEA VIRHE");
			ex.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException ex1) {
				System.err.println("Rollbakki epäonnistui!");
			}
		} finally {
			TietokantaApu.close(conn);
			TietokantaApu.close(lisaaHlo);
			TietokantaApu.close(rs);
		}
	}
	
	private static void asetaArvot(String a, String b, String c, double d, String e, PreparedStatement target) 
			throws SQLException {
		target.clearParameters();
		target.setString(1, a);
		target.setString(2, b);
		target.setString(3, c);
		target.setDouble(4, d);
		target.setString(5, e);
		target.executeUpdate();
	}
}
