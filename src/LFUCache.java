
public class LFUCache implements Cache {

	private Subscriptie[] cacheElements;
	private int timeStamp;
	private int[] timeElements;
	private int[] accessElements;

	// constructor LRUCache
	public LFUCache(int cacheDimension) {
		cacheElements = new Subscriptie[cacheDimension];
		timeStamp = 0;
		timeElements = new int[cacheDimension];
		accessElements = new int[cacheDimension];
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
				accessElements[i] = 1;
				return;
			}
		}

		int minAccess = timeStamp; // initializam nr. acces minim cu o valoare foarte mare
		int minTime = 0;
		int index = 0;
		// daca nu este loc in cache, gasim cel mai putin folosit element(minAccess)
		// si il suprascriem
		for (i = 0; i < accessElements.length; i++) {
			if (minAccess > accessElements[i]) {
				minAccess = accessElements[i];
				minTime = timeElements[i];
				index = i; // salvam indexul
			}
			// daca numarul de accesari al elementului este egal cu minimul
			// si este cel mai vechi element cu aceasta valoare
			if (accessElements[i] == minAccess) {
				if (minTime > timeElements[i]) {
					minTime = timeElements[i];
					minAccess = accessElements[i];
					index = i;
				}
			}
		}

		// adaugam elementul pe pozitia 'index'
		cacheElements[index] = element;
		// actualizam timeStamp
		timeElements[index] = timeStamp;
		timeStamp++;
		accessElements[index] = 1;
	}

	@Override
	public void remove(String name) {
		for (int i = 0; i < cacheElements.length; i++) {
			if (cacheElements[i] != null && cacheElements[i].getName().equals(name)) {
				cacheElements[i] = null;
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
				accessElements[i]++;
				return cacheElements[i];
			}
		}
		return null;
	}

}
