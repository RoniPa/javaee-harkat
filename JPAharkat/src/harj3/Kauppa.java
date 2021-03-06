package harj3;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KAUPPA")
public class Kauppa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="osoite")
	private String osoite;
	@Column(name="nimi")
	private String nimi;
	
	public Kauppa(){
		this.osoite = null;
		this.nimi = null;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	@Override
	public String toString(){
		return this.id + " " + this.nimi + ", osoite: " + this.osoite;
	}
}
