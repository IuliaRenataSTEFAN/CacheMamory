
public class Premium extends Basic {

	private int nrPremium;

	public Premium(String name, int nrBasic, int nrPremium) {
		super(name, nrBasic);
		this.nrPremium = nrPremium;
	}

	public void decPremium() {
		nrPremium--;
	}

	//reutrneaza tipul obiectului cat si abdateaza numarul de cereri
	public String getType() {
		if (nrPremium > 0) {
			decPremium();
			return "Premium";
		} else if (getNrBasic() > 0) {
			decBasic();
			return "Basic";
		} else {
			return "Free";
		}
	}
}
