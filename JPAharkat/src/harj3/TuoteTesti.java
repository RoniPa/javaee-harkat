package harj3;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TuoteTesti {
	public static void main(String[] args){
		Tuote t1 = new Tuote();
		t1.setNimi("Pesäpallo");
		t1.setHinta(15.56);
		t1.setKoodi("A321");
		Tuote t2 = new Tuote();
		t2.setNimi("Jalkapallo");
		t2.setHinta(19.99);
		t2.setKoodi("A654");
		Tuote t3 = new Tuote();
		t3.setNimi("Koripallo");
		t3.setHinta(24.99);
		t3.setKoodi("B342");
		 
		EntityManagerFactory tehdas = Persistence.createEntityManagerFactory("jpa_harjoitukset");
		EntityManager manageri = tehdas.createEntityManager();
		
		EntityTransaction transaktio = manageri.getTransaction();
		
		transaktio.begin();
		manageri.persist(t1);
		manageri.persist(t2);
		manageri.persist(t3);
		transaktio.commit();
		
		List<Tuote> results = manageri.createNamedQuery("selectAllTuote", Tuote.class)
				.getResultList();
		
		System.out.println("\n--- TUOTE TESTIHAKU ---\n");
		int max = results.size();
		for (int i = 0; i < max; i++)
			System.out.println(results.get(i).toString());
	}
}
