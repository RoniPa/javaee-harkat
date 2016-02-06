package bookjpa;

import java.util.List;

import javax.persistence.*;

public class TestBookJpa {

	public static void main(String[] args) {
		 EntityManagerFactory tehdas = Persistence.createEntityManagerFactory("jpa_book");
	        EntityManager manageri = tehdas.createEntityManager();

	        EntityTransaction transaktio = manageri.getTransaction();
	        
	        transaktio.begin();

	        Book b1 = new Book();
	        b1.setIsbn("A123");
	        b1.setNimi("Core Java");
	        b1.setTekijat("Cay Horstmann");

	        Book b2 = new Book();
	        b2.setIsbn("A123");
	        b2.setNimi("JavaScript Ninja");
	        b2.setTekijat("John Resig");
	        
	        Book b3 = new Book();
	        b3.setIsbn("A123");
	        b3.setNimi("Thinking Of Java");
	        b3.setTekijat("Bruce Eckel");
	        
	        manageri.persist(b1);
	        manageri.persist(b2);
	        manageri.persist(b3);
	        
	        transaktio.commit();

	        // Testihaku - haetaan ja tulostetaan kaikki kannassa olevat Language-entiteetit
	        @SuppressWarnings("unchecked")
			List<Book> entiteetit = manageri.createNamedQuery("etsiKaikkiKirjat").getResultList();
	        for (Book b : entiteetit) {
	            System.out.println("Rivi: " + b.getNimi());
	        }

	        manageri.close();
	        tehdas.close();
	}

}
