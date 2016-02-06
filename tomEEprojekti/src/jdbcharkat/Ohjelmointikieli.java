package jdbcharkat;

import java.sql.Date;

public class Ohjelmointikieli {
	private Integer id;
	private String nimi;
	private String versio;
	private String suunnittelija;
	private Date julkaistu;
	
	public Ohjelmointikieli(){
		this.id = null;
		this.nimi = null;
		this.versio = null;
		this.suunnittelija = null;
		this.julkaistu = null;
	}

	@Override
	public String toString() {
		return "Ohjelmointikieli [id=" + id + ", nimi=" + nimi + ", versio=" + versio + ", suunnittelija="
				+ suunnittelija + ", julkaistu=" + julkaistu + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getVersio() {
		return versio;
	}

	public void setVersio(String versio) {
		this.versio = versio;
	}

	public String getSuunnittelija() {
		return suunnittelija;
	}

	public void setSuunnittelija(String suunnittelija) {
		this.suunnittelija = suunnittelija;
	}

	public Date getJulkaistu() {
		return julkaistu;
	}

	public void setJulkaistu(Date julkaistu) {
		this.julkaistu = julkaistu;
	}
}
