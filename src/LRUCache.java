
public class LRUCache implements Cache {

	private Subscriptie[] cacheElements;
	private int timeStamp;
	private int[] timeElements;

	// constructor LRUCache
	public LRUCache(int cacheDimension) {
		cacheElements = new Subscriptie[cacheDimension];
		timeStamp = 0;
		timeElements = new int[cacheDimension];
	}

	@Override
	public void add(Subscriptie element) {
		int i;
		// daca este loc in cache, adaugam elementul
		for (i = 0; i < cacheElements.length; i++) {
			if (cacheElements[i] == null) {
				cacheElements[i] = element;
				// actualizam timeStamp
				timeElements[i] = timeStamp;
				timeStamp++;
				return;
			}
		}

		int min = timeStamp; // initializam minimul cu o valoare foarte mare
		int index = 0;
		// daca nu este loc in cache, gasim cel mai vechi folosit element(min)
		// si il suprascriem
		for (i = 0; i < timeElements.length; i++) {
			if (min >= timeElements[i]) {
				min = timeElements[i];
				index = i; // salvam indexul
			}
		}
		// adaugam elementul pe pozitia 'index'
		cacheElements[index] = element;
		// actualizam timeStamp
		timeElements[index] = timeStamp;
		timeStamp++;
	}

	@Override
	public void remove(String name) {
		for (int i = 0; i < cacheElements.length; i++) {
			if (cacheElements[i] != null && cacheElements[i].getName().equals(name)) {
				cacheElements[i] = null;
				timeElements[i] = 0;
			}
		}
	}

	// cautam in cache elementul
	@Override
	public Subscriptie searchCache(String name) {
		for (int i = 0; i < cacheElements.length; i++) {
			if (cacheElements[i] != null && cacheElements[i].getName().equals(name)) {
				timeElements[i] = timeStamp;
				timeStamp++;
				return cacheElements[i];
			}
		}
		return null;
	}
}
