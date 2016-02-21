package jdbcharkat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Tehtava7Main {
	//final private static String query = "SELECT Monivalinta.*, Vaihtoehto.* FROM Monivalinta, Vaihtoehto WHERE Monivalinta.id=Vaihtoehto.kys_id";
	final private static String query1 = "SELECT * FROM Monivalinta";
	final private static String query2 = "SELECT * FROM Vaihtoehto";
	
	public static void main(String args[]){
		Connection conn = TietokantayhteysTehdas.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Map<Integer, MonivalintaDTO> kysymykset = new HashMap<>();
		
		try {
			// haetaan kysymykset
			
			stmt = conn.prepareStatement(query1);
			rs = stmt.executeQuery();
						
			while (rs.next()) {
				MonivalintaDTO m = new MonivalintaDTO();
				m.setId(rs.getInt(1));
				m.setKysymys(rs.getString(2));
				kysymykset.put(rs.getInt(1), m);
			}
			
			TietokantaApu.close(stmt);
			TietokantaApu.close(rs);
			
			// haetaan vaihtoehdot
			
			stmt = conn.prepareStatement(query2);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				VaihtoehtoDTO v = new VaihtoehtoDTO();
				v.setId(rs.getInt(1));
				v.setKysymysId(rs.getInt(2));
				v.setTeksti(rs.getString(3));
				v.setOikein(rs.getBoolean(4));
				kysymykset.get(v.getKysymysId()).getVaihtoehdot().add(v);
			}
			
			/*
			kysymykset.values().forEach((m)->{
				System.out.println(m.getKysymys());
				System.out.println(m.getVaihtoehdot().stream().map(VaihtoehtoDTO::getTeksti)
						.collect(Collectors.joining(", ")) + "\n");
			});
			*/
			
		} catch (SQLException ex) {
			System.err.println("Tässäpä vasta kumma pulma: " + ex);
		} finally {
			TietokantaApu.close(conn);
			TietokantaApu.close(stmt);
			TietokantaApu.close(rs);
		}
	}
}
