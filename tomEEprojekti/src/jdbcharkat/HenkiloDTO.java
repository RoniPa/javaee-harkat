package jdbcharkat;
import java.math.BigDecimal;
import java.sql.Date;

public class HenkiloDTO implements Comparable<HenkiloDTO>{
	private String etunimi,sukunimi,sotu;
	private BigDecimal palkka;
	private Date syntymaaika;
	
	public HenkiloDTO() {
		this.etunimi = "";
		this.sukunimi = "";
		this.sotu = "";
		this.palkka = null;
		this.syntymaaika = null;
	}

	@Override
	public String toString() {
		return "HenkiloDTO [etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", sotu=" + sotu + ", palkka=" + palkka
				+ ", syntymaaika=" + syntymaaika + "]";
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public String getSotu() {
		return sotu;
	}

	public void setSotu(String sotu) {
		this.sotu = sotu;
	}

	public BigDecimal getPalkka() {
		return palkka;
	}

	public void setPalkka(BigDecimal palkka) {
		this.palkka = palkka;
	}

	public Date getSyntymaaika() {
		return syntymaaika;
	}

	public void setSyntymaaika(Date syntymaaika) {
		this.syntymaaika = syntymaaika;
	}

	@Override
	public int compareTo(HenkiloDTO h1) {
		int comp;
		
		if (this == h1) return 0;
		
		comp = this.getSukunimi().compareTo(h1.getSukunimi());
		if (comp != 0) return comp;
		
		comp = this.getEtunimi().compareTo(h1.getEtunimi());
		if (comp != 0) return comp;
		
		comp = this.getSotu().compareTo(h1.getSotu());
		if (comp != 0) return comp;
		
		comp = this.getSyntymaaika().compareTo(h1.getSyntymaaika());
		if (comp != 0) return comp;
		
		comp = this.getPalkka().compareTo(h1.getPalkka());
		if (comp != 0) return comp;
		
		return 0;
	}
}
