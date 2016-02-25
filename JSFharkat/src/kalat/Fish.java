package kalat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@SessionScoped
public class Fish implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String species;  // kalalaji
    private String name;     // lempinimi
    private int weight;      // paino
    private int length;      // pituus
    private boolean editable; 

    public Fish() { }
    
	@Override
	public String toString() {
		return "Fish [species=" + species + ", name=" + name + ", weight="
				+ weight + " kg, length=" + length + " m, editable=" + editable
				+ "]";
	}
	
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
    
}	