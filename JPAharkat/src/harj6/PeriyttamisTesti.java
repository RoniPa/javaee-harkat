package harj6;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PeriyttamisTesti {
	public static void main(String args[]){
		EntityManagerFactory tehdas = Persistence.createEntityManagerFactory("jpa_harjoitukset");
		EntityManager manageri = tehdas.createEntityManager();
		
		lisaaData(manageri);
		
		// haetaan kirjat
		
		List<Kirja> kirjat = 
				manageri.createQuery("SELECT a FROM Kirja a", Kirja.class)
				.getResultList();
		
		System.out.println("\n***********Kaikki kirjat***********");
		for(Kirja k : kirjat){
			System.out.println(k);
		}
		
		// haetaan kalliit cd:t
		
		List<Cd> kalliitCdt = 
					manageri.createQuery("SELECT a FROM Cd a WHERE a.hinta > :rajahinta", Cd.class)
					.setParameter("rajahinta", 15).getResultList();
		
		System.out.println("\n***********Kalliit levyt***********");
		for(Cd cd : kalliitCdt){
			System.out.println(cd);
		}
		
		// haetaan halvat tuotteet
		
		List<KantaTuote> halvatTuotteet = 
					manageri.createQuery("SELECT a FROM KantaTuote a WHERE a.hinta < :rajahinta", KantaTuote.class)
					.setParameter("rajahinta", 15).getResultList();
		
		System.out.println("\n***********Halvat tuotteet***********");
		for(KantaTuote t : halvatTuotteet){
			System.out.println(t);
		}
		
		manageri.close();
		tehdas.close();
	}
	
	private static void lisaaData(EntityManager manageri){
		Kirja k1 = new Kirja(); 
		k1.setIsbn("A123"); 
		k1.setNimi("Core Java"); 
		k1.setHinta(26.99); 
		k1.setTekijat("Cay Horstmann");
		
		Kirja k2 = new Kirja(); 
		k2.setIsbn("A223"); 
		k2.setNimi("JavaScript Ninja"); 
		k2.setTekijat("John Resig"); 
		k2.setHinta(33.50);
		
		Kirja k3 = new Kirja(); 
		k3.setIsbn("A313"); 
		k3.setNimi("Thinking Of Java"); 
		k3.setTekijat("Bruce Eckel"); 
		k3.setHinta(9.95);
		
		Cd cd1 = new Cd("Iron Maiden", "Powerslave", 3048, 8, 19.95); 
		Cd cd2 = new Cd("Tom Petty", "Full Moon Fever", 2398, 12, 14.95); 
		Cd cd3 = new Cd("Paula Koivuniemi", "Luotan Sydämen Ääneen", 2850, 12, 4.95); 
		Cd cd4 = new Cd("The Beatles", "Abbey Road", 2545, 17, 29.95);
		
		EntityTransaction transaktio = manageri.getTransaction();
		
		transaktio.begin();
		manageri.persist(k1);
		manageri.persist(k2);
		manageri.persist(k3);
		manageri.persist(cd1);
		manageri.persist(cd2);
		manageri.persist(cd3);
		manageri.persist(cd4);
		transaktio.commit();
	}
}
