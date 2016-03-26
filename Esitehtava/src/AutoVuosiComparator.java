import java.util.Comparator;

/**
 * J‰rjest‰‰ autot ensisijaisesti vuosiluvun perusteella pienimm‰st‰ suurimpaan,
 * sitten merkin (valmistajan) ja lopulta mallin mukaan.
 * 
 * @author Roni
 *
 */

public class AutoVuosiComparator implements Comparator<Auto> {
	
	@Override
	public int compare(Auto arg0, Auto arg1) {
		int vuosiluku = (arg0.getValmistusvuosi() > arg1.getValmistusvuosi()) ?
				1 : (arg0.getValmistusvuosi() < arg1.getValmistusvuosi()) ?
						-1 : 0;
		
		if (vuosiluku == 0){
			int valmistaja = arg0.getMerkki().compareTo(arg1.getMerkki());
			if (valmistaja == 0){
				return arg0.getMalli().compareTo(arg1.getMalli());
			} else return valmistaja;
		} else return vuosiluku;
	}
}
