package harj1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SimpleEntityTest {
	// "Tietorakenne" entityiden luomisen avuksi
	private static class Teksti {
		public final String n;
		public final String d;
		
		public Teksti(String s1, String s2) {
			this.n = s1;
			this.d = s2;
		}
	}
	
	public static void main(String args[]){
		Teksti[] names = {
			new Teksti("Pallo", "Py�re� esine"),
	        new Teksti("Kello", "Mittaa aikaa"),
	        new Teksti("Kenk�", "Vaate jalalle"),
	        new Teksti("Kala", "Vedess� asuva eli�"),
	        new Teksti("Kukka", "N�tti ja tuoksuu hyv�lle")
		};
		
		EntityManagerFactory tehdas = Persistence.createEntityManagerFactory("jpa_harjoitukset");
		EntityManager manageri = tehdas.createEntityManager();
		
		EntityTransaction transaktio = manageri.getTransaction();
		
		transaktio.begin();
		for(Teksti t : names) addEntity(t.n, t.d, manageri);
		transaktio.commit();
		
		// -- Haetaan kaikki -- //
		
		List<?> results = manageri.createNamedQuery("selectAll")
					.getResultList();
		
		System.out.println("\n********************** KAIKKI ENTITYT ***************************\n");
		int max = results.size();
		for (int i = 0; i < max; i++)
			System.out.println("Tulos: " + results.get(i).toString());
		
		// -- Haetaan vain K-alkuiset -- //
				
		results = manageri.createQuery(
						"SELECT a from SimpleEntity a WHERE a.name LIKE 'K%'", 
						SimpleEntity.class
					).getResultList();
		
		System.out.println("\n********************** K-alkuiset ***************************\n");
		max = results.size();
		for (int i = 0; i < max; i++)
			System.out.println("Tulos: " + results.get(i).toString());
		
		// -- Haetaan a-kirjaimen sis�lt�v�t -- //
		
		results = manageri.createQuery(
						"SELECT a from SimpleEntity a WHERE a.name LIKE '%A%'", 
						SimpleEntity.class
					).getResultList();
		
		System.out.println("\n********************** Sis�lt�v�t a-kirjaimen ***************************\n");
		max = results.size();
		for (int i = 0; i < max; i++)
			System.out.println("Tulos: " + results.get(i).toString());
		
		// -- Parametrisoitu haku -- //
		
		TypedQuery<SimpleEntity> query = manageri.createQuery(
						"SELECT a from SimpleEntity a WHERE a.name LIKE :name", 
						SimpleEntity.class
					);
				
		results = query.setParameter("name", "%l%").getResultList();
		
		System.out.println("\n********************** Parametrihaku ***************************\n");
		max = results.size();
		for (int i = 0; i < max; i++)
			System.out.println("Tulos: " + results.get(i).toString());
	}
	
	private static void addEntity(String name, String desc, EntityManager m){
		SimpleEntity e = new SimpleEntity();
		e.setName(name);
		e.setDesc(desc);
		m.persist(e);
	}
}
