package jdbcharkat;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Tehtava8Main {
	public static void main(String args[]){
		OhjelmointikieliDAO dao = new OhjelmointikieliDAO();
		List<Ohjelmointikieli> kaikki = null;
		
		try {
			Ohjelmointikieli o = new Ohjelmointikieli();
			o.setNimi("Testikieli");
			o.setSuunnittelija("Eero Esimerkki");
			o.setVersio("0.1");
			o.setJulkaistu(Date.valueOf("2016-01-29"));
			// dao.lisaaOhjelmointikieli(o);

			kaikki = dao.getOhjelmointikielet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Ohjelmointikieli kieli: kaikki) {
			System.out.println(kieli);
		}
		
		// Kymmenen vanhinta kieltä
		
		dao.setJatkokysely("order by julkaistu limit 10");
		
		try {
			kaikki = dao.getOhjelmointikielet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nVanhimmat:");
		for (Ohjelmointikieli kieli: kaikki) {
			System.out.println(kieli);
		}
	}
}
