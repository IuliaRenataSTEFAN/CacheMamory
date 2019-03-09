
public interface Cache {

	public void add(Subscriptie subscription);

	public void remove(String name);

	public Subscriptie searchCache(String name);
}
