package kalat;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped        
public class FishController implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public FishController() {}
	
    /* @return null (error) tai success */
    public String tarkista() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        // JSF:ssa luodun beanin nimellä päästään olioon kiinni "fish" (faces-config.xml)
        Fish f = (Fish)facesContext.getExternalContext().getSessionMap().get("fish");
        int a = f.getLength();
        int b = f.getWeight();
        
        if (a <= 0 || b <= 0) { // Onko pituus tai paino nolla tai negatiivinen!
            return "FishError"; // virhe. ohjaus virhesivulle - FishError.xhtml
        }
        return "FishOutput"; // Suora ohjaus tiedostoon - FishOutput.xhtml
    }   
}
