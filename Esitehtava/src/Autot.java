import java.util.ArrayList;
import java.util.Collections;

public class Autot{
	ArrayList<Auto> autot;
	
	public Autot(){
		this.autot = new ArrayList<>();
	}
	public Autot(ArrayList<Auto> autot){
		this.autot = autot;		
	}
	public ArrayList<Auto> getAutot(){
		return this.autot;
	}
	public void setAutot(ArrayList<Auto> a){
		this.autot = a;
	}
	public void setAuto(Auto a){
		this.autot.add(a);
	}
	/**
	 * Palauttaa kopion autolistasta j‰rjestettyn‰ vanhimmasta uusimpaan.
	 * J‰rjest‰‰ autot Collections.sort() metodilla.
	 * @return ArrayList<Auto>
	 */
	public ArrayList<Auto> sorted(){
		ArrayList<Auto> listaKopio = new ArrayList<Auto>(this.autot);
		Collections.sort(listaKopio, new AutoVuosiComparator());
		return listaKopio;
	}
	
	@Override
	public String toString() {
		return autot.toString();
	}
}
