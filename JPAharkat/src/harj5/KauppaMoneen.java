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
@Table(name="KAUPPA_MONEEN")
public class KauppaMoneen implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="osoite")
	private String osoite;
	@Column(name="nimi")
	private String nimi;
	@ManyToMany(mappedBy="kaupat", targetEntity=TuoteMoneen.class)
	private List<TuoteMoneen> tuotteet;
	
	public KauppaMoneen(){
		this.osoite = null;
		this.nimi = null;
		this.tuotteet = new ArrayList<>();
	}
	
	public void setTuotteet(List<TuoteMoneen> t){
		this.tuotteet = t;
		
		for(int i = 0; i < t.size(); i++){
			t.get(i).getKaupat().add(this);
		}
	}
	public List<TuoteMoneen> getTuotteet(){
		return this.tuotteet;
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
