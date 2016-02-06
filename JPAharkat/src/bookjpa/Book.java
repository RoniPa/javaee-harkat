package bookjpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Book
 *
 */
@javax.persistence.Entity
@Table(name="BOOK") // JPQL - JPA 2.0 tai JPA 2.1
@NamedQuery(name = "etsiKaikkiKirjat", query = "SELECT b from Book b")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "isbn")
	private String isbn;
	@Column(name = "tekijat")
	private String tekijat;
	@Column(name = "nimi")
	private String nimi;

	public Book() {
	    } 

	public Book(String isbn, String tekijat, String nimi) {
	        this.isbn = isbn;
	        this.tekijat = tekijat;
	        this.nimi = nimi;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "[" + nimi + "," + isbn + "," + tekijat + "]";
	}


}
