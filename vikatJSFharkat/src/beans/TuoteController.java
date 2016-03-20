package beans;

import java.util.Collections;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class TuoteController implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public TuoteController() {}
    
    /* @return null (error) tai success */
    public String lisaa() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        Tuote t = (Tuote)facesContext
        		.getExternalContext().getRequestMap().get("tuote");
        
        Tuotteet tuotteet = (Tuotteet)facesContext
        		.getExternalContext().getSessionMap().get("tuotteet");
        
        tuotteet.getTuotteet().add(t);
        
        Collections.sort(tuotteet.getTuotteet());
        
        return "tulosta-tuotteet?faces-redirect=true"; // Suora ohjaus tiedostoon - tulosta-tuotteet.xhtml
    }
}