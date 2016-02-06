package jdbcharkat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/* 
 * TietokantaApu
 * 
 * Tietokantayhteyksien sulkemisen apuluokka. 
 * @author Juha Peltomäki
 */


public class TietokantaApu {

	public static void close(Connection yhteys) {
		if (yhteys != null) {
			try {
				yhteys.close();
			} catch (SQLException e) {
				System.out.println("VIRHE: Kannan sulkeminen ei onnistunut.");
			}
		}
	}

	public static void close(Statement lauseke) {
		if (lauseke != null) {
			try {
				lauseke.close();
			} catch (SQLException e) {
				System.out.println("VIRHE: Kannan sulkeminen ei onnistunut.");
			}
		}
	}

	public static void close(ResultSet tulosjoukko) {
		if (tulosjoukko != null) {
			try {
				tulosjoukko.close();
			} catch (SQLException e) {
				System.out.println("VIRHE: Kannan sulkeminen ei onnistunut.");
			}
		}
	}
}
