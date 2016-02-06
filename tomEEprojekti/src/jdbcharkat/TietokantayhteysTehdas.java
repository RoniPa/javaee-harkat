package jdbcharkat;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* TietokantayhteysTehdas

Tätä käytetään kaikissa esimerkeissä ja harjoituksissa.
Sisältää tuen eri tietokannoille tehden tietokannan vaihtamisesta melko vaivatonta.
 
Luokka kapseloi sisäänsä kaikki tietokantayhteyden avaaminen liittyvät asiat
  
Tukee eri tietokantoja, kunhan muuttujaa TIETOKANTA muutetaan.
  
Tukee seuraavia tietokantoja 
  MySQL
  DB2 
  H2 
  
 * @author Juha Peltomäki
 */

public class TietokantayhteysTehdas {
	public enum TK {
		MySQL, H2
	}

	// Valitse mitä tietokantaa käytetään.
	private static final Enum<?> TIETOKANTA = TK.MySQL; // käytetään MySQL:ää
	//private static final Enum<?> TIETOKANTA = TK.H2; // käytetään h2:sta

	// Tietoyhteyksien alustamiseen käytettävät staattiset muuttujat
	private static TietokantayhteysTehdas instance = new TietokantayhteysTehdas();

	public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/jee";
	public static final String MYSQL_KAYTTAJA = "root";
	public static final String MYSQL_SALASANA = "";

	// H2 kannan asetukset
	//public static final String H2_URL = "jdbc:h2:tcp://localhost/~/softat/h2/demo;DATABASE_TO_UPPER=false;MODE=MySQL"; // palvelinmoodi, h2.jar käynnistettävä erikseen.
	//public static final String H2_URL = "jdbc:h2:mem:harkat;DB_CLOSE_DELAY=1200"; // muistikanta
	public static final String H2_URL = "jdbc:h2:~/softat/h2/demo;AUTO_SERVER=TRUE"; // mixed moodi, palvelin lokaalisti, ei tarvitse käynnistetä h2:sta vaan ohjelma käynnistää.
	private static final String H2_KAYTTAJA = "";
	private static final String H2_SALASANA = "";

	// DB2 kannan asetukset

	// privaatti konstruktori, ei voi kutsua ulkopuolelta
	private TietokantayhteysTehdas() {
		// ei tarvita enää Java 6 ja uudemmissa JDBC ajurien lataamista!
	}

	private Connection luoYhteys() {
		Connection yhteys = null;
		try {
			System.out.println("Tietokanta: " + TIETOKANTA);
			if (TIETOKANTA == TK.MySQL)
				yhteys = DriverManager.getConnection(MYSQL_URL, MYSQL_KAYTTAJA, MYSQL_SALASANA);
			else if (TIETOKANTA == TK.H2)
				yhteys = DriverManager.getConnection(H2_URL, H2_KAYTTAJA, H2_SALASANA);

		} catch (SQLException e) {
			System.out.println("VIRHE: Kytkeytyminen kantaan ei onnistunut." + e);
		}
		return yhteys;
	}

	public static Connection getConnection() {
		// kutsuu privaattia konstruktoria luoden olion ja palauttaa luoYhteys()
		// metodilla luodun yhteyden
		return instance.luoYhteys();
	}
}
