import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class Main {

	public static void main(String args[]) throws FileNotFoundException {

		// citim din fisier
		File file = new File(args[0]);
		Scanner scanner = new Scanner(file);
		PrintStream out = new PrintStream(new FileOutputStream(args[1]));
		System.setOut(out);
		String memoType = scanner.nextLine();
		int cacheDimension = scanner.nextInt();
		int nrInstruction = scanner.nextInt();
		Cache cache = null;
		MyArray mainMemory = new MyArray();

		if (memoType.equals("LRU")) {
			cache = new LRUCache(cacheDimension);
		}

		if (memoType.equals("FIFO")) {
			cache = new FIFOCache(cacheDimension);
		}

		if (memoType.equals("LFU")) {
			cache = new LFUCache(cacheDimension);
		}

		for (int i = 0; i < nrInstruction; i++) {
			// citim instructiunea si numele obiectului
			String instruction = scanner.next();
			String name = scanner.next();
			// daca instructiunea este 'ADD',
			// citim din fisier numarul de cereri basic
			if (instruction.equals("ADD")) {
				int nrBasic = scanner.nextInt();
				Subscriptie object;
				// daca mai exista inca un int in comanda
				if (scanner.hasNextInt()) {
					// obiectul este de tip premium
					int nrPremium = scanner.nextInt();
					object = new Premium(name, nrBasic, nrPremium);
					// altfel este de tip basic
				} else {
					object = new Basic(name, nrBasic);
				}
				// daca gasim un obiect cu acc. nume cu obiectul pe care vrem sa il adaugam
				// in memoria principala
				if (mainMemory.searchMemory(name) != null) {
					// il suprascriem si adaugam noul obiect
					mainMemory.overwrite(object);
					cache.remove(name);
					// daca nu exista un astfel de obiect deja
				} else {
					// il adaugam pe cel nou
					mainMemory.add(object);
				}
			}
			// daca instructiunea primita este 'GET'
			if (instruction.equals("GET")) {
				// cautam subscriptia in cache
				Subscriptie subscription = cache.searchCache(name);
				// daca am gasit obiectul in cache (subscription != null)
				if (subscription != null) {
					// afisam '0' si tipul subscriptiei
					System.out.println("0 " + subscription.getType());
					// daca nu am gasit obiectul in cache
				} else {
					// inseamna ca poate sa fie in memoria principala
					// => cautam in mainMemory
					subscription = mainMemory.searchMemory(name);
					// daca exista in mainMemory
					if (subscription != null) {
						// afisam '1' si tipul subscriptiei
						System.out.println("1 " + subscription.getType());
						cache.add(subscription);
					} else
						// altfel, afisam '2'
						System.out.println("2");
				}
			}
		}
		scanner.close();
	}
}
