package harj3;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Yksi-moneen -demo
 * @author Roni P-a
 */

public class KauppaTesti {
	public static void main(String args[]){
		EntityManagerFactory tehdas = Persistence.createEntityManagerFactory("jpa_harjoitukset");
		EntityManager manageri = tehdas.createEntityManager();
		
		lisaaData(manageri);
		manageri.close();
		
		// TODO: Testikysely palauttaa tietokantaan tallennetut tiedot ñ haku ja tulostus
		
		// Avataan uusi yhteys
		manageri = tehdas.createEntityManager();
		
		List<?> results = manageri.createQuery("SELECT a FROM Tuote a")
				.getResultList();
		
		System.out.println("\n--- TUOTETAULU ---\n");
		int max = results.size();
		for (int i = 0; i < max; i++){
			System.out.println((Tuote)results.get(i));
		}
		
		results = manageri.createQuery("SELECT a FROM Kauppa a")
				.getResultList();
		
		System.out.println("\n--- KAUPPATAULU ---\n");
		max = results.size();
		for (int i = 0; i < max; i++){
			System.out.println((Kauppa)results.get(i));
		}
		
		// Ohjelma tulostaa tiedot, mist‰ kaupasta tuotteet lˆytyy
		
		List<Tuote> results2 = manageri.createNamedQuery("selectAllTuote", Tuote.class)
				.getResultList();
		
		System.out.println("\n--- TUOTTEET KAUPOISSA ---\n");
		max = results2.size();
		for (int i = 0; i < max; i++)
			System.out.println(results2.get(i).getNimi() + ": "
					+ results2.get(i).getKaupat().toString());
	}
	
	private static void lisaaData(EntityManager manageri){
		// Luodaan tuotteet
		
		Tuote t1 = new Tuote();
		t1.setNimi("Pes‰pallo");
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
		
		List<Tuote> tuotteet = new ArrayList<>();
		tuotteet.add(t1);
		tuotteet.add(t2);
		tuotteet.add(t3);
		
		// Luodaan kaupat
		
        Kauppa k1 = new Kauppa();
        k1.setNimi("Palloliike");
        k1.setOsoite("Pallokuja 2, Helsinki");
        Kauppa k2 = new Kauppa();
        k2.setNimi("V‰lineaitta");
        k2.setOsoite("Kauppatie 45, Jyv‰skyl‰");
        Kauppa k3 = new Kauppa();
        k3.setNimi("Verkkosportti");
        k3.setOsoite("Kuplahallintie 15, Toijala");
        
		List<Kauppa> kaupat = new ArrayList<>();
		kaupat.add(k1);
		kaupat.add(k2);
		kaupat.add(k3);
		
		// Lis‰t‰‰n tuotteille kaupat
        // t1 ñ kaupat [k1, k2]
        // t3 ñ kaupat [k2, k3]
		
		tuotteet.get(0).getKaupat().add(kaupat.get(0));
		tuotteet.get(0).getKaupat().add(kaupat.get(1));
		tuotteet.get(2).getKaupat().add(kaupat.get(1));
		tuotteet.get(2).getKaupat().add(kaupat.get(2));
		
		EntityTransaction transaktio = manageri.getTransaction();
		
		transaktio.begin();
		for(int i = 0; i < tuotteet.size(); i++)
			manageri.persist(tuotteet.get(i));
		for(int i = 0; i < kaupat.size(); i++)
			manageri.persist(kaupat.get(i));
		transaktio.commit();
	}
}
