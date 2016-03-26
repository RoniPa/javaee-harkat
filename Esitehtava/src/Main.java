
public class Main {
	public static void main(String args[]){
		Autot t = new Autot();
		t.setAuto(new Auto("Toyota", "Corolla", 1966));
		t.setAuto(new Auto("Mazda", "626", 1979));
		t.setAuto(new Auto("Volkswagen", "Kupla", 1950));
		t.setAuto(new Auto("Ford", "Escort", 1967));
		t.setAuto(new Auto("Nissan", "Primera", 1990));
		t.setAuto(new Auto("Saab", "900", 1978));
		t.setAuto(new Auto("Audi", "80", 1972));
		t.setAuto(new Auto("Skoda", "Octavia", 1959));
		t.setAuto(new Auto("Chrysler", "New Yorker", 1936));
		t.setAuto(new Auto("Ford", "T-Ford", 1908));
		
		System.out.println(t);
		System.out.println(t.sorted());
	}
}
