package harj6;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="KIRJA")
public class Kirja extends KantaTuote{
	private static final long serialVersionUID = 1L;
	
	private String isbn; 
	private String tekijat;
	
	public Kirja(){
		super();
		this.isbn = null;
		this.tekijat = null;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTekijat() {
		return tekijat;
	}

	public void setTekijat(String tekijat) {
		this.tekijat = tekijat;
	}
	
	@Override
	public String toString() {
		return "Kirja [id=" + this.getId() + ", nimi=" + this.getNimi() + ", tekijat=" + tekijat 
				+ ", isbn=" + isbn + ", hinta=" + this.getHinta() + "]";
	}
}
