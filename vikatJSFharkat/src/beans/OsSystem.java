package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class OsSystem implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String name="ABC Operation System";
    private String versio="0.11";
    
	public OsSystem() {
		super();
	}
	
	public OsSystem(String name, String versio) {
		super();
		this.name = name;
		this.versio = versio;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersio() {
		return versio;
	}
	public void setVersio(String versio) {
		this.versio = versio;
	}

	@Override
	public String toString() {
		return "OsSystem [name=" + name + ", versio=" + versio + "]";
	}
}
