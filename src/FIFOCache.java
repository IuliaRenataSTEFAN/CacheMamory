
public class FIFOCache implements Cache {

	private Subscriptie[] cacheElements;

	// constructor FIFOCache
	public FIFOCache(int cacheDimension) {
		cacheElements = new Subscriptie[cacheDimension];
	}

	@Override
	public void add(Subscriptie element) {
		// daca este loc, punem elemente de la dreapta la stanga in cache
		for (int i = cacheElements.length - 1; i >= 0; i--) {
			if (cacheElements[i] == null) {
				cacheElements[i] = element;
				return;
			}
		}
		// daca nu este loc, shiftam la dreapta si suprascriem primul element
		// pe care l-am inserat
		for (int i = cacheElements.length - 1; i > 0; i--) {
			cacheElements[i] = cacheElements[i - 1];
		}
		// adaugam noul element, in stanga
		cacheElements[0] = element;
	}

	@Override
	public void remove(String name) {
		for (int i = 0; i < cacheElements.length; i++) {
			if (cacheElements[i] != null && cacheElements[i].getName().equals(name)) {
				cacheElements[i] = null;
				for (int j = i; j > 0; j--) {
					cacheElements[j] = cacheElements[j - 1];
				}
				cacheElements[0] = null;
				return;
			}
		}
	}

	// cautam in cache elementul
	@Override
	public Subscriptie searchCache(String name) {
		for (int i = 0; i < cacheElements.length; i++) {
			if (cacheElements[i] != null && cacheElements[i].getName().equals(name)) {
				return cacheElements[i];
			}
		}
		return null;
	}
}
