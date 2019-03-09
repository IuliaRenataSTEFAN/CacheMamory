
public class Basic extends Free {

	private int nrBasic;

	public Basic(String name, int nrBasic) {
		super(name);
		this.nrBasic = nrBasic;
	}

	public int getNrBasic() {
		return nrBasic;
	}

	public void decBasic() {
		nrBasic--;
	}

	@Override
	public String getType() {
		if (nrBasic > 0) {
			decBasic();
			return "Basic";
		} else {
			return "Free";
		}
	}

}
