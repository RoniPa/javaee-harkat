package harj6;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
// Kullekin Entitylle ominaiset arvot tallennetaan omaan tauluun
@Inheritance(strategy=InheritanceType.JOINED)
// Kaikki Entityt tallennetaan samaan tauluun
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//Joka Entitylle tehd‰‰n oma taulu
// @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class KantaTuote implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id; 
	private String nimi; 
	private double hinta; 
	
	public KantaTuote(){
		this.nimi = null;
		this.hinta = 0;	
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	
	public Long getId(){
		return this.id;
	}
	
	@Override
	public String toString() {
		return "KantaTuote [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta + "]";
	}
} 
