
public abstract class Subscriptie {

	private String name;

	public Subscriptie(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public abstract String getType();
}
