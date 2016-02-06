package jdbcharkat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OhjelmointikieliDAO {
	final static private String KYSELY = "SELECT * FROM ohjkieli ";
	final static private String LISAA = "INSERT INTO ohjkieli(nimi, viimversio, suunnittelija, julkaistu) VALUES (?, ?, ?, ?)";
	
	private Connection yhteys;
	private PreparedStatement lause;
	private String jatkokysely;
	
	public OhjelmointikieliDAO(){
		this.yhteys = TietokantayhteysTehdas.getConnection();
		this.lause = null;
		this.jatkokysely = "";
	}
	
	// KYSELY per‰‰n liitett‰v‰ jatkokysely
	// palauttaa listan tietokannasta kyselyn perusteella saaduista ohjelmointikielist‰
	public List<Ohjelmointikieli> getOhjelmointikielet() throws SQLException {		
		List<Ohjelmointikieli> l = new ArrayList<>();
		
		ResultSet rs = this.haku(null);
		while(rs.next()){
			l.add(luoUusi(rs));
		}
		this.alustaLause();
		return l.size() > 0 ? l : null;
	}
	
	// palauttaa yhden ohjelmointikielen
	public Ohjelmointikieli getOhjelmointikieli(int kieliId) throws SQLException {
		this.jatkokysely = "WHERE id=?";
		Ohjelmointikieli o = null;
		
		ResultSet rs = this.haku(kieliId);
		if(rs.next()) o = luoUusi(rs);
		this.alustaLause();
		return o;
	}
	
	public void lisaaOhjelmointikieli(Ohjelmointikieli o) throws SQLException{
		if (
			o.getJulkaistu() != null &&
			o.getNimi() != null &&
			o.getSuunnittelija() != null &&
			o.getVersio() != null
		) {
			this.lause = this.yhteys.prepareStatement(LISAA);
			this.lause.setString(1, o.getNimi());
			this.lause.setString(2, o.getVersio());
			this.lause.setString(3, o.getSuunnittelija());
			this.lause.setString(4, o.getJulkaistu().toString());
			this.lause.executeUpdate();
			this.alustaLause();
		} else throw new SQLException("Ohjelmointikielt‰ ei voida lis‰t‰, tietoja puuttuu");
	}
	
	public void setJatkokysely(String jatkokysely){
		this.jatkokysely = jatkokysely;
	}
	
	@Override
	protected void finalize() throws Throwable {
		TietokantaApu.close(this.yhteys);
	}
	
	private ResultSet haku(Integer id) throws SQLException {
		if (id != null) {
			this.lause = this.yhteys.prepareStatement(KYSELY + this.jatkokysely);
			this.lause.setInt(1, id);
		} else if (this.jatkokysely != null) {
			this.lause = this.yhteys.prepareStatement(KYSELY + this.jatkokysely);
		} else this.lause = this.yhteys.prepareStatement(KYSELY);
		
		ResultSet rs = this.lause.executeQuery();
		return rs;
	}
	
	private void alustaLause() throws SQLException{
		TietokantaApu.close(this.lause);
		this.jatkokysely = null;
	}
	
	private static Ohjelmointikieli luoUusi(ResultSet rs) throws SQLException{
		Ohjelmointikieli o = new Ohjelmointikieli();
		o.setId(rs.getInt(1));
		o.setNimi(rs.getString(2));
		o.setVersio(rs.getString(3));
		o.setSuunnittelija(rs.getString(4));
		o.setJulkaistu(rs.getDate(5));
		return o;
	}
}
