package harj5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="TUOTE_MONEEN")
public class TuoteMoneen implements Serializable{
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
	@ManyToMany(targetEntity=KauppaMoneen.class)
	private List<KauppaMoneen> kaupat;
	
	public TuoteMoneen(){
		this.nimi = null;
		this.koodi = null;
		this.hinta = 0;
		this.kaupat = new ArrayList<>();
	}
	
	public List<KauppaMoneen> getKaupat() {
		return kaupat;
	}

	public void setKaupat(List<KauppaMoneen> kaupat) {
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