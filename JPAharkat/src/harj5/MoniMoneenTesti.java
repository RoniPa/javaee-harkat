package harj5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MoniMoneenTesti {
	public static void main(String[] args){
		EntityManagerFactory tehdas = Persistence.createEntityManagerFactory("jpa_harjoitukset");
		EntityManager manageri = tehdas.createEntityManager();
		
		lisaaData(manageri);
		
		// SQL-queryt tauluihin
		
		Query tuoteQuery = manageri.createNativeQuery("SELECT * FROM tuote_moneen", TuoteMoneen.class); 
		Query kauppaQuery = manageri.createNativeQuery("SELECT * FROM kauppa_moneen", KauppaMoneen.class);
		Query liitosQuery = manageri.createNativeQuery("SELECT * FROM tuote_moneen_kauppa_moneen");
		
		List<?> tuotteet = tuoteQuery.getResultList();
		List<?> kaupat = kauppaQuery.getResultList();
		List<?> liitos = liitosQuery.getResultList();
		
		for(Object t : tuotteet){
			System.out.println((TuoteMoneen)t);
		}
		for(Object k : kaupat){
			System.out.println((KauppaMoneen)k);
		}
		for(Object o : liitos){
			System.out.println(genToString(o));
		}
		
		// Tulostetaan tuotteet ja kaupat
		tulostaTuotteetJaKaupat(manageri);
		
		// Tulostetaan kaupat ja tuotteet
		tulostaKaupatJaTuotteet(manageri);
		
		manageri.close();
		tehdas.close();
	}
	
	private static void tulostaTuotteetJaKaupat(EntityManager manageri){
		Query query2 = manageri.createNativeQuery(
				"SELECT DISTINCT t1.ID, t1.HINTA, t1.KOODI, t1.NIMI, "
				+ "t0.ID, t0.NIMI, t0.OSOITE "
				+ "FROM kauppa_moneen t0, tuote_moneen_kauppa_moneen t2, tuote_moneen t1 "
				+ "WHERE ((t2.tuotteet_ID = t1.ID) AND (t0.ID = t2.kaupat_ID))", TuoteMoneen.class);
		
		List<?> tulokset = query2.getResultList();
		
		System.out.println("\n******************Tuotteet ja kaupat*****************\n");
		for(Object o : tulokset){
			//System.out.println(genToString(o));
			TuoteMoneen t = (TuoteMoneen)o;
			System.out.println(t + ", kaupat: " + t.getKaupat());
		}
	}
	
	private static void tulostaKaupatJaTuotteet(EntityManager manageri){
		Query query2 = manageri.createNativeQuery(
				"SELECT t0.ID, t0.NIMI, t0.OSOITE, "
				+ "t1.ID, t1.HINTA, t1.KOODI, t1.NIMI "
				+ "FROM kauppa_moneen t0 "
				+ "LEFT OUTER JOIN tuote_moneen_kauppa_moneen t2 "
				+ "ON t0.ID = t2.kaupat_ID "
				+ "LEFT OUTER JOIN tuote_moneen t1 "
				+ "ON t2.tuotteet_ID = t1.ID "
				+ "GROUP BY t0.ID", KauppaMoneen.class);
		
		List<?> tulokset = query2.getResultList();
		
		System.out.println("\n******************Kaupat ja tuotteet*****************\n");
		for(Object o : tulokset){
			//System.out.println(genToString(o));
			KauppaMoneen k = (KauppaMoneen)o;
			System.out.println(k + ", tuotteet: " + k.getTuotteet());
		}
	}
	
	private static void lisaaData(EntityManager manageri){
		// Luodaan tuotteet
		
		TuoteMoneen t1 = new TuoteMoneen();
		t1.setNimi("Pesäpallo");
		t1.setHinta(15.56);
		t1.setKoodi("A321");
		TuoteMoneen t2 = new TuoteMoneen();
		t2.setNimi("Jalkapallo");
		t2.setHinta(19.99);
		t2.setKoodi("A654");
		TuoteMoneen t3 = new TuoteMoneen();
		t3.setNimi("Koripallo");
		t3.setHinta(24.99);
		t3.setKoodi("B342");
		
		// Luodaan kaupat
		
        KauppaMoneen k1 = new KauppaMoneen();
        k1.setNimi("Palloliike");
        k1.setOsoite("Pallokuja 2, Helsinki");
        KauppaMoneen k2 = new KauppaMoneen();
        k2.setNimi("Välineaitta");
        k2.setOsoite("Kauppatie 45, Jyväskylä");
        KauppaMoneen k3 = new KauppaMoneen();
        k3.setNimi("Verkkosportti");
        k3.setOsoite("Kuplahallintie 15, Toijala");
		
        List<TuoteMoneen> tuotteet2 = new ArrayList<>();
        tuotteet2.add(t1);
        tuotteet2.add(t3);
        List<TuoteMoneen> tuotteet = new ArrayList<>();
        tuotteet.add(t1);
        List<TuoteMoneen> tuotteet3 = new ArrayList<>();
        tuotteet3.add(t3);
        
        k1.setTuotteet(tuotteet);
        k2.setTuotteet(tuotteet2);
        k3.setTuotteet(tuotteet3);
        
        EntityTransaction transaktio = manageri.getTransaction();
		
		transaktio.begin();
		manageri.persist(t1);
		manageri.persist(t2);
		manageri.persist(t3);
		manageri.persist(k1);
		manageri.persist(k2);
		manageri.persist(k3);
		transaktio.commit();
	}
	
	/**
	 * Palauttaa geneerisen querytuloksen tekstimuodossa
	 * @param o
	 * @return String
	 */
	private static String genToString(Object o){
	    if (o instanceof Object[])
	        return Arrays.asList((Object[])o).toString();
	    else return String.valueOf(o);
	}
}
