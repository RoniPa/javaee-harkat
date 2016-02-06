package jdbcharkat;

public class VaihtoehtoDTO {
	private Integer id;
	private Integer kysymysId;
	private String teksti;
	private Boolean oikein;
	
	public VaihtoehtoDTO(){
		this.id = null;
		this.kysymysId = null;
		this.teksti = null;
		this.oikein = null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKysymysId() {
		return kysymysId;
	}
	public void setKysymysId(int kysymysId) {
		this.kysymysId = kysymysId;
	}
	public String getTeksti() {
		return teksti;
	}
	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}
	public Boolean getOikein() {
		return oikein;
	}
	public void setOikein(Boolean oikein) {
		this.oikein = oikein;
	}
}
