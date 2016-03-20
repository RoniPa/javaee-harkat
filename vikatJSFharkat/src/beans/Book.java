package beans;

public class Book implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String isbn, tekijat, nimi;
	
	public Book() {
		super();
	}

	public Book(String isbn, String tekijat, String nimi) {
		super();
		this.isbn = isbn;
		this.tekijat = tekijat;
		this.nimi = nimi;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTekijat() {
		return tekijat;
	}

	public void setTekijat(String tekijat) {
		this.tekijat = tekijat;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", tekijat=" + tekijat + ", nimi=" + nimi + "]";
	}
}
