package jdbcharkat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Tehtava6Main {
	final static String lauseet[] = {
			"CREATE TABLE Monivalinta (id int not null, kysymys varchar(255), primary key ( id ))",
			"CREATE TABLE Vaihtoehto (id int not null auto_increment, kys_id int, teksti varchar(255), oikein boolean, foreign key (kys_id) references Monivalinta(id), primary key ( id ))",
			"Insert into Monivalinta (id, kysymys) values (1, 'Ruotsin pääkaupunki')",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (1, 'Oslo', false)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (1, 'Tukholma', true)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (1, 'Göteborg', false)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (1, 'Luulaja', false)",
			"Insert into Monivalinta (id, kysymys) values (2, 'Suosituin ohjelmointikieli')",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (2, 'C', false)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (2, 'C#', false)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (2, 'Java', true)",
			"Insert into Monivalinta (id, kysymys) values (3, 'Web selainten ohjelmointikieli')",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (3, 'TypeX', false)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (3, 'perl', false)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (3, 'JavaScript', true)",
			"Insert into Monivalinta (id, kysymys) values (4, 'Käytetyin merkintäkieli Webissä')",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (4, 'XML', false)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (4, 'JavaScript', false)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (4, 'HTML', true)",
			"Insert into Monivalinta (id, kysymys) values (5, 'JavaEE Framework?')",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (5, 'JSF', true)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (5, 'JPA', true)",
			"Insert into Vaihtoehto (kys_id, teksti, oikein) values (5, 'EJB', true)"
		};
	
	public static void main(String args[]) {
		Connection conn = TietokantayhteysTehdas.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			for (String l : lauseet){
				stmt.executeUpdate(l);
			}
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Monivalinta,Vaihtoehto");
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			while (rs.next()) {
				String tuloste = "";
				for (int j = 1; j <= colCount; j++){
					tuloste += rs.getString(j) + "\t"; 
				}
				System.out.println(tuloste);
			}

			stmt.close();
			conn.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
	}
}
