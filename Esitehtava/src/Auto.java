
public class Auto {
	private String merkki;
	private String malli;
	private int valmistusvuosi;
	
	public Auto(){
		merkki = null;
		malli = null;
		valmistusvuosi = 0;
	}
	public Auto(String merkki, String malli, int vuosi){
		this.merkki = merkki;
		this.malli = malli;
		this.valmistusvuosi = vuosi;
	}
		
	public String getMerkki() {
		return merkki;
	}
	public void setMerkki(String merkki) {
		this.merkki = merkki;
	}
	public String getMalli() {
		return malli;
	}
	public void setMalli(String malli) {
		this.malli = malli;
	}
	public int getValmistusvuosi() {
		return valmistusvuosi;
	}
	public void setValmistusvuosi(int valmistusvuosi) {
		this.valmistusvuosi = valmistusvuosi;
	}
	
	@Override
	public String toString() {
		return "Auto: " + merkki + " " + malli + ", " + valmistusvuosi + "\n";
	}
}
