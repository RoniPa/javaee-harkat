package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.*;

@ManagedBean
public class Tuote implements Comparable<Tuote>,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Size(min=1, max=40)
    private String nimi;
    @Size(min=1, max=20)
    private String koodi;
    @Min(0)
    @Max(5000)
    private double hinta;
    
    public Tuote(){
    	super();
    }
    
    public Tuote(String nimi, String koodi, double hinta){
    	this.nimi = nimi;
    	this.koodi = koodi;
    	this.hinta = hinta;
    }
    
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public String getKoodi() {
		return koodi;
	}
	public void setKoodi(String koodi) {
		this.koodi = koodi;
	}
	public double getHinta() {
		return hinta;
	}
	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	@Override
	public String toString() {
		return "Tuote [nimi=" + nimi + ", koodi=" + koodi + ", hinta=" + hinta + "]";
	}
	
	/**
	 * Järjestää tuotteet ensin nimen, sitten koodin 
	 * ja lopulta hinnan mukaan suuruusjärjestykseen.
	 */
	@Override
	public int compareTo(Tuote arg0) {
		int nimi = this.nimi.compareTo(arg0.getNimi());
		if (nimi == 0) {
			int koodi = this.koodi.compareTo(arg0.getKoodi());
			if (koodi == 0){
				double arg0hinta = arg0.getHinta();
				if (this.hinta > arg0hinta) return 1;
				else if (this.hinta < arg0hinta) return -1;
				else return 0;
			} else return koodi;
		} else return nimi;
	}
}
