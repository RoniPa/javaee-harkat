package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tuotteet {
	List<Tuote> tuotteet;
	
	public Tuotteet(){
		this.tuotteet = new ArrayList<>();
	}

	public List<Tuote> getTuotteet() {
		return tuotteet;
	}

	public void setTuotteet(ArrayList<Tuote> tuotteet) {
		this.tuotteet = tuotteet;
	}
	
	public void setTuote(Tuote tuote) {
		this.tuotteet.add(tuote);
	}
	
	@Override
	public String toString(){
		String output = "";
		
		Iterator<Tuote> i = this.tuotteet.iterator();
		while(i.hasNext()){
			output += i.next().toString();
		}
		
		return output;
	}
}
