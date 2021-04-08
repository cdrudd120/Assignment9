package assign09;

import java.util.ArrayList;
import java.util.Random;

public class HashTableTimer {

	public static void main(String[] args) {
		// Do 1000 lookups and use the average running time
		int timesToLoop = 100;
		Random randomNumberGenerator = new Random();
		// For each problem size n . . .
		for (int n = 10000; n <= 200000; n += 10000) {

			// Uses graphInput to build the needed arrays
			HashTable<Integer, String> studentUIDTable = new HashTable<Integer, String>();

//			// makes arraylists of n length with numbers 0-9
//			for (int i = 0; i < n; i++) {
//				int UID = 1000000 + i;
//				String firstName = (char) ('A' + i % 26) + "" + (char) ('b' + i % 26) + (char) ('c' + i % 26);
//				String lastName = (char) ('D' + i % 26) + "" + (char) ('e' + i % 26) + (char) ('f' + i % 26);
//				StudentBadHash newHash = new StudentBadHash(UID, firstName, lastName);
//				studentUIDTable.put(newHash.hashCode(), lastName + firstName);
//
////							for(MapEntry<Integer, String> e : studentUIDTable.entries())
////								System.out.println("Student " + e.getKey() + " has name " + e.getValue() + ".");
//			}

			long startTime, midpointTime, stopTime;
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				// Lookup the last item in the array,
				// so method will not return early
				for (int k = 0; k < n; k++) {
					// testTree.contains(j);
				}
				for (int i = 0; i < n; i++) {
					int UID = 1000000 + i;
					String firstName = (char) ('A' + i % 26) + "" + (char) ('b' + i % 26) + (char) ('c' + i % 26);
					String lastName = (char) ('D' + i % 26) + "" + (char) ('e' + i % 26) + (char) ('f' + i % 26);
					StudentGoodHash newHash = new StudentGoodHash(UID, firstName, lastName);
					studentUIDTable.put(newHash.hashCode(), lastName + firstName);
				}
			}

			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println((n * timesToLoop) + "\t" + averageTime);
		}
	}

}
