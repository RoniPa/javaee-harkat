package jdbcharkat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tehtava5Main {
	final private static String HAKU = "SELECT * FROM henkilo WHERE sukunimi=? OR sukunimi=?";

	public static void main(String[] args) {
		Connection conn = TietokantayhteysTehdas.getConnection();
		PreparedStatement haeHlo = null;
		ResultSet rs = null;
		List<HenkiloDTO> henkilot = new ArrayList<>();
		
		try {
			haeHlo = conn.prepareStatement(HAKU);
			haeHlo.setString(1, "Ankka");
			haeHlo.setString(2, "Hiiri");
			
			rs = haeHlo.executeQuery();
			while(rs.next()){
				HenkiloDTO h = new HenkiloDTO();
				h.setEtunimi(rs.getString("etunimi"));
				h.setSukunimi(rs.getString("sukunimi"));
				h.setSotu(rs.getString("sotu"));
				h.setSyntymaaika(rs.getDate("syntymaaika"));
				h.setPalkka(rs.getBigDecimal("palkka"));
				henkilot.add(h);
			}
			
			/*
			System.out.println("**************Tulokset (Ankka ja Hiiri)**************");
			henkilot.stream().sorted().forEach((h)->{
				String output = h.getEtunimi() + " " + h.getSukunimi()
					+ "(" + h.getSotu() + ") syntyi " + h.getSyntymaaika().toString()
					+ " ja saa palkkaa " + h.getPalkka().toString();
				
				System.out.println(output);
			});
			*/
		} catch(SQLException ex){
			System.err.println("Tapahtui virhe :(");
		} finally {
			TietokantaApu.close(conn);
			TietokantaApu.close(haeHlo);
			TietokantaApu.close(rs);
		}
	}

}
