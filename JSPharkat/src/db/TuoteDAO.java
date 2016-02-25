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
	
	public static TuoteDAO instance = null;
	private Connection c;
	
	/**
	 * Sisäinen konstruktori, luo instanssin
	 */
	private TuoteDAO(){
		this.c = TietokantayhteysTehdas.getConnection();
	}
	
	/**
	 * Palauttaa instanssin daosta
	 * @return
	 */
	public static synchronized TuoteDAO getInstance() {
		if (TuoteDAO.instance == null)
			TuoteDAO.instance = new TuoteDAO();
		
		return TuoteDAO.instance;
	}
	
	/**
	 * Sulkee yhteyden
	 */
	public void close() {
		TietokantaApu.close(this.c);
		TuoteDAO.instance = null;
	}
	
	/**
	 * Palauttaa kaikki tuotteet tuotetaulusta
	 * @return Tuotteet
	 * @throws SQLException
	 */
	public beans.Tuotteet haeKaikki() throws SQLException{
		Statement stmt = this.c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM tuotetaulu");
		
		return convertToTuotteet(rs);
	}
	
	/**
	 * Palauttaa kaikki arvot annetun taulun mukaan j�rjestettyn�
	 * @param taulu Verrattava taulu
	 * @param jarjestys TuoteDAO.OrderBy
	 * @return Tuotteet
	 * @throws SQLException
	 */
	public beans.Tuotteet haeJarjestyksessa(Column taulu, OrderBy jarjestys)
		throws SQLException {
		
		Statement stmt = this.c.createStatement();
		
		// Voidaan tehd� suoralla queryll�,
		// koska enum (ei SQL-injektion riski�)
		
		ResultSet rs = stmt.executeQuery(
				"SELECT * FROM tuotetaulu "
				+"ORDER BY "+taulu+" "+jarjestys );

		return convertToTuotteet(rs);
	}
	
	/**
	 * Haetaan tulokset testivertailulla annettuun tauluun
	 * @param taulu taulu, jonka arvoihin verrataan
	 * @param haku vertailtava teksti
	 * @return Tuotteet
	 * @throws SQLException
	 */
	public beans.Tuotteet haeTekstilla(Column taulu, String haku)
			throws SQLException {
					
		PreparedStatement stmt = this.c.prepareStatement(
				"SELECT * FROM tuotetaulu "
				+"WHERE "+taulu+" LIKE ?");
		
		stmt.setString(1, "%"+haku+"%");
		ResultSet rs = stmt.executeQuery();
		
		return convertToTuotteet(rs);
	}
	
	/**
	 * Lis�� annetun Tuote-olion tietokantaan
	 * @param t Tuote
	 * @return true jos suoritettu query
	 * @throws SQLException
	 */
	public boolean lisaaTuote(beans.Tuote t)
			throws SQLException {
	
		if (t.getNimi()!= null 
			&& t.getKoodi() != null 
			&& t.getHinta() != null) {
			
			PreparedStatement stmt = this.c.prepareStatement(
					"INSERT INTO tuotetaulu (nimi, koodi, hinta)"
					+"VALUES(?,?,?)");
			
			stmt.setString(1, t.getNimi());
			stmt.setString(2, t.getKoodi());
			stmt.setDouble(3, t.getHinta());
			stmt.execute();
			return true;
			
		} else throw new SQLException("Tuotetta ei voi lis�t�, arvoja puuttuu");
	}
	
	/**
	 * P�ivitt�� annetun tuotteen tiedot tietokantaan
	 * @param t
	 * @return true jos suoritettu query
	 * @throws SQLException
	 */
	public boolean paivitaTuote(Column c, String haku, beans.Tuote t)
			throws SQLException {
		
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
				// k�ytt�j�n sy�tteet sy�tet��n parametreina
				
				PreparedStatement stmt = this.c.prepareStatement(
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
				
				// Validoidaan id (jos se on asetettu p�ivitysehto)
				switch (c) {
					case TUOTEID :
						try {
							int id = Integer.parseInt(haku);
							stmt.setInt(paramInd, id);
						} catch (NumberFormatException ex) {
							return false;
						}
						break;
					
					// Muut hyv�ksytyt arvot tekstimuotoisia,
					// joten voidaan k�sitell� oletusarvolla
					
					default: stmt.setString(paramInd, haku);
				}
				
				stmt.execute();
				return true;
				
			} else {
				throw new SQLException("Tuotetta ei voi p�ivitt�� hinnan perusteella");
			}
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
