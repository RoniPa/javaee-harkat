package beans;

public class Tuote {
	private String nimi;
	private String koodi;
	private Double hinta;
	
	public Tuote(){
		this.nimi = null;
		this.koodi = null;
		this.hinta = null;
	}
	public Tuote(String nimi, String koodi, Double hinta){
		this.nimi = nimi;
		this.koodi = koodi;
		this.hinta = hinta;
	}
	
	public String getKoodi() {
		return koodi;
	}
	public void setKoodi(String koodi) {
		this.koodi = koodi;
	}
	
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public Double getHinta() {
		return hinta;
	}
	public void setHinta(Double hinta) {
		this.hinta = hinta;
	}
	
	@Override
	public String toString(){
		return "Tuote[nimi="+this.nimi
				+", koodi="+this.koodi
				+", hinta="+this.hinta
				+"]";
	}
}
