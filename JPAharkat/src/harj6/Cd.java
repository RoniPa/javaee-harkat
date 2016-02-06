package harj6;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CD")
public class Cd extends KantaTuote{
	private static final long serialVersionUID = 1L;
	
	private String artisti; 
	private int pituus; 
	private int biiseja; 
	
	public Cd(){
		super();
		this.artisti = null;
		this.pituus = 0;
		this.biiseja = 0;
	}

	public Cd(String artisti, String nimi, int pituus, int biiseja, double hinta){
		super();
		this.setNimi(nimi);
		this.setHinta(hinta);
		this.artisti = artisti;
		this.pituus = pituus;
		this.biiseja = biiseja;
	}

	public String getArtisti() {
		return artisti;
	}

	public void setArtisti(String artisti) {
		this.artisti = artisti;
	}

	public int getPituus() {
		return pituus;
	}

	public void setPituus(int pituus) {
		this.pituus = pituus;
	}

	public int getBiiseja() {
		return biiseja;
	}

	public void setBiiseja(int biiseja) {
		this.biiseja = biiseja;
	}
	
	@Override
	public String toString() {
		return "Cd [id=" + getId() + ", nimi=" + getNimi() + ", artisti=" + artisti 
				+ ", pituus=" + pituus + ", biiseja=" + biiseja 
				+ ", hinta=" + getHinta() + "]";
	}
}
