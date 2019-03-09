
public class MyArray {

	private Subscriptie[] memory;
	private int index;

	// constructor MyArray
	public MyArray() {
		memory = new Subscriptie[100];
		index = 0;
	}

	public void add(Subscriptie object) {
		if (index >= memory.length) {
			Subscriptie[] auxMemory = new Subscriptie[2 * memory.length];
			for (int i = 0; i < memory.length; i++) {
				auxMemory[i] = memory[i];
			}
			memory = auxMemory;
		}
		memory[index] = object;
		index++;
	}

	public Subscriptie get(int index) {
		return memory[index];
	}

	public int size() {
		return memory.length;
	}

	// cautam in memorie obiectul si il returnam daca exista
	public Subscriptie searchMemory(String name) {
		for (int i = 0; i < index; i++) {
			if (memory[i].getName().equals(name)) {
				return memory[i];
			}
		}
		return null;
	}

	// suprascriem vechiul obiect cu cel nou adaugat
	// in memoria principala
	public void overwrite(Subscriptie object) {
		for (int i = 0; i < index; i++) {
			if (memory[i].getName().equals(object.getName())) {
				memory[i] = object;
			}
		}
	}
}
