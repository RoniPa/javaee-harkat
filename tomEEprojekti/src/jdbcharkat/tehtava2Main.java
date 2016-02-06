package jdbcharkat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Testiqueryjä tietokantaan
 * @author Roni
 *
 */

public class tehtava2Main {
	final static String a = "SELECT * FROM henkilo";
	final static String b = "SELECT * FROM henkilo ORDER BY id DESC";
	
	
	public static void main(String args[]){
		Connection conn = TietokantayhteysTehdas.getConnection();
		
		try {			
			Statement stmt = conn.createStatement();
			
			ResultSet rs1 = stmt.executeQuery(a);
			ResultSetMetaData rsmd1 = rs1.getMetaData();
			
			int columnCount = rsmd1.getColumnCount();
			int rowCount = 0;
			
			String output = "****************************kaikki*************************************\n";
			while(rs1.next()){
				rowCount++;
				for (int i = 1; i <= columnCount; i++){
					output += rs1.getString(rsmd1.getColumnName(i)) +"\t";
				}
				output += "\n";
			}
			System.out.println(output + "\n");
			
			//ResultSet rs2 = stmt.executeQuery(b);
			//ResultSetMetaData rsmd2 = rs2.getMetaData();
			
			output = "*****************************************toisinpäin************************************\n";
			while(rs1.previous()){				
				for (int i = 1; i <= columnCount; i++){
					output += rs1.getString(rsmd1.getColumnName(i)) +"\t";
				}
				output += "\n";
			}
			System.out.println(output + "\n");
			
			System.out.println("Tulosjoukon rivien lkm: " + rowCount +"\n");
			
			int p = rowCount / 2;
			rs1.absolute(p);

			output = "*************************************puolesta välistä********************************\n";
			while(rs1.next()){				
				for (int i = 1; i <= columnCount; i++){
					output += rs1.getString(rsmd1.getColumnName(i)) +"\t";
				}
				output += "\n";
			}
			System.out.println(output + "\n");
			
			rs1.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException ex){
			System.out.println(ex);
			// ex.printStackTrace();
		}
	}
}
