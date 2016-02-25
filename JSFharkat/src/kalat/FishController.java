package kalat;

import javax.faces.application.FacesMessage;
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
        
        //Keeping faces message after redirect (JSF)
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        
        // JSF:ssa luodun beanin nimellä päästään olioon kiinni "fish" (faces-config.xml)
        Fish f = (Fish)facesContext.getExternalContext().getSessionMap().get("fish");
        
        int a = f.getLength();
        int b = f.getWeight();
        
        if (a <= 0 || b <= 0) { // Onko pituus tai paino nolla tai negatiivinen!
        	facesContext.addMessage(null, new FacesMessage("Length or Weight (" + a + "," + b + ") should be greater than zero!"));
            return "FishError?faces-redirect=true";
        }
        return "FishOutput?faces-redirect=true"; // Suora ohjaus tiedostoon - FishOutput.xhtml
    }
}
