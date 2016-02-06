package harj3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TUOTE")
@NamedQuery(name = "selectAllTuote", query = "SELECT a from Tuote a")
public class Tuote implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="koodi")
	private String koodi;
	@Column(name="nimi")
	private String nimi;
	@Column(name="hinta")
	private double hinta;
	@OneToMany(targetEntity=Kauppa.class, fetch=FetchType.EAGER)
	private List<Kauppa> kaupat;
	
	public Tuote(){
		this.nimi = null;
		this.koodi = null;
		this.hinta = 0;
		this.kaupat = new ArrayList<>();
	}
	
	public List<Kauppa> getKaupat() {
		return kaupat;
	}

	public void setKaupat(List<Kauppa> kaupat) {
		this.kaupat = kaupat;
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
	public String toString(){
		return "Tuote " + this.koodi 
				+ ", nimi: " +this.nimi 
				+ ", hinta: " + this.hinta;
	}
}