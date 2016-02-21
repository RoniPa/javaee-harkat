package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TuoteDAO {
	public static enum OrderBy {
		ASC, DESC;
	}
	
	public static enum Column {
		TUOTEID, NIMI, KOODI, HINTA
	}
	
	public static Connection c; 
	
	/**
	 * Alustaa yhteyden
	 */
	public static void init() {
		if (TuoteDAO.c == null)
			TuoteDAO.c = TietokantayhteysTehdas.getConnection();
	}
	
	/**
	 * Sulkee yhteyden
	 */
	public static void close() {
		if (TuoteDAO.c != null){
			TietokantaApu.close(TuoteDAO.c);
		}
	}
	
	/**
	 * Palauttaa kaikki tuotteet tuotetaulusta
	 * @return Tuotteet
	 * @throws SQLException
	 */
	public static beans.Tuotteet haeKaikki() throws SQLException{
		if (TuoteDAO.c != null){			
			Statement stmt = TuoteDAO.c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM tuotetaulu");
			
			return convertToTuotteet(rs);
		} else throw new SQLException("Yhteyttä ei ole alustettu!");
	}
	
	/**
	 * Palauttaa kaikki arvot annetun taulun mukaan järjestettynä
	 * @param taulu Verrattava taulu
	 * @param jarjestys TuoteDAO.OrderBy
	 * @return Tuotteet
	 * @throws SQLException
	 */
	public static beans.Tuotteet haeJarjestyksessa(Column taulu, OrderBy jarjestys)
		throws SQLException {
		
		if (TuoteDAO.c != null){			
			Statement stmt = TuoteDAO.c.createStatement();
			
			// Voidaan tehdä suoralla queryllä,
			// koska enum (ei SQL-injektion riskiä)
			
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM tuotetaulu "
					+"ORDER BY "+taulu+" "+jarjestys );

			return convertToTuotteet(rs);
		} else throw new SQLException("Yhteyttä ei ole alustettu!");
	}
	
	/**
	 * Haetaan tulokset testivertailulla annettuun tauluun
	 * @param taulu taulu, jonka arvoihin verrataan
	 * @param haku vertailtava teksti
	 * @return Tuotteet
	 * @throws SQLException
	 */
	public static beans.Tuotteet haeTekstilla(Column taulu, String haku)
			throws SQLException {
		
		if (TuoteDAO.c != null){			
			PreparedStatement stmt = TuoteDAO.c.prepareStatement(
					"SELECT * FROM tuotetaulu "
					+"WHERE "+taulu+" LIKE ?");
			
			stmt.setString(1, "%"+haku+"%");
			ResultSet rs = stmt.executeQuery();
			
			return convertToTuotteet(rs);
		} else throw new SQLException("Yhteyttä ei ole alustettu!");
	}
	
	/**
	 * Lisää annetun Tuote-olion tietokantaan
	 * @param t Tuote
	 * @return true jos suoritettu query
	 * @throws SQLException
	 */
	public static boolean lisaaTuote(beans.Tuote t)
		throws SQLException {
		
		if (TuoteDAO.c != null){
			if (t.getNimi()!= null 
				&& t.getKoodi() != null 
				&& t.getHinta() != null) {
				
				PreparedStatement stmt = TuoteDAO.c.prepareStatement(
						"INSERT INTO tuotetaulu (nimi, koodi, hinta)"
						+"VALUES(?,?,?)");
				
				stmt.setString(1, t.getNimi());
				stmt.setString(2, t.getKoodi());
				stmt.setDouble(3, t.getHinta());
				stmt.execute();
				return true;
				
			} else throw new SQLException("Tuotetta ei voi lisätä, arvoja puuttuu");
		} else throw new SQLException("Yhteyttä ei ole alustettu!");
	}
	
	/**
	 * Päivittää annetun tuotteen tiedot tietokantaan
	 * @param t
	 * @return true jos suoritettu query
	 * @throws SQLException
	 */
	public static boolean paivitaTuote(Column c, String haku, beans.Tuote t)
			throws SQLException {
		
		if (TuoteDAO.c != null){
			if (c != Column.HINTA) {
				
				// Luodaan oikea update-query
				String params = "SET ";
				
				int paramInd = 1;
				if (t.getNimi() != null) {
					params += " nimi=?";
					paramInd++;
				}
				if (t.getKoodi() != null) {
					if (paramInd > 1) params += ",";
					params += " koodi=?";
					paramInd++;
				}
				if (t.getHinta() != null) {
					if (paramInd > 1) params += ",";
					params += " hinta=? ";
				}
				
				// c = Column enum, params DAO:ssa generoitu SQL
				// käyttäjän syötteet syötetään parametreina
				
				PreparedStatement stmt = TuoteDAO.c.prepareStatement(
						"UPDATE tuotetaulu "
						+ params + "WHERE "+c+" = ?");
				
				paramInd = 1;
				if (t.getNimi() != null) {
					stmt.setString(paramInd, t.getNimi());
					paramInd++;
				}
				if (t.getKoodi() != null) {
					stmt.setString(paramInd, t.getKoodi());
					paramInd++;
				}
				if (t.getHinta() != null) {
					stmt.setObject(paramInd, t.getHinta());
					paramInd++;
				}
				
				// Validoidaan id (jos se on asetettu päivitysehto)
				switch (c) {
					case TUOTEID :
						try {
							int id = Integer.parseInt(haku);
							stmt.setInt(paramInd, id);
						} catch (NumberFormatException ex) {
							return false;
						}
						break;
					
					// Muut hyväksytyt arvot tekstimuotoisia,
					// joten voidaan käsitellä oletusarvolla
					
					default: stmt.setString(paramInd, haku);
				}
				
				stmt.execute();
				return true;
				
			} else {
				throw new SQLException("Tuotetta ei voi päivittää hinnan perusteella");
			}
		} else throw new SQLException("Yhteyttä ei ole alustettu!");
	}
	
	/**
	 * Muuntaa ResultSetin Tuotteet-olioksi
	 * @param rs ResultSet tuotetaulusta
	 * @return Tuotteet
	 * @throws SQLException
	 */
	private static beans.Tuotteet convertToTuotteet(ResultSet rs)
		throws SQLException{
		
		beans.Tuotteet t = new beans.Tuotteet();
		
		while (rs.next()){
			t.setTuote( new beans.Tuote(
						rs.getString("nimi"), 
						rs.getString("koodi"), 
						rs.getDouble("hinta")));
		}
		return t;
	}
}
