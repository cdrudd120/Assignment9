package assign09;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HashTableTimer {

	public static void main(String[] args) {
		// Do 1000 lookups and use the average running time
		
		char[] arr = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t',
				'u','v','w','x','y','z'};
		
		Random randomNumberGenerator = new Random();
		List<Integer> uIDs = new ArrayList<Integer>();
		List<String> randNames = new ArrayList<String>();
		for (int i = 0; i <= 10000; i++) {
			String newName = "";
			for(int j = 0; j < 6; j++) {
				int num = randomNumberGenerator.nextInt(26);
				char letter = arr[num];
				newName += letter + "";
			}
			randNames.add(newName);
			int UID = 1000000 + i;
			uIDs.add(UID);
		}
		
		int timesToLoop = 200;
		
		// For each problem size n . . .
		for (int n = 1000; n <= 10000; n += 1000) {
			HashTable<StudentGoodHash, String> studentUIDTable = new HashTable<StudentGoodHash, String>();
			StudentGoodHash newHash;
			for (int i = 0; i <= n; i++) {
				newHash = new StudentGoodHash(uIDs.get(i), randNames.get(i), randNames.get(randNames.size() - i - 1));
				
				studentUIDTable.put(newHash, "a");
			}
			
			long startTime, midpointTime, stopTime;
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				StudentGoodHash randomStudent = new StudentGoodHash(uIDs.get(j), randNames.get(j), randNames.get(randNames.size() - j - 1));
				studentUIDTable.put(randomStudent, "b");
			}

			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) { // empty block
				StudentGoodHash randomStudent = new StudentGoodHash(uIDs.get(i), randNames.get(i), randNames.get(randNames.size() - i - 1));
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println((n) + "\t" + averageTime + "\t" + studentUIDTable.getCollisions());
		}
	}

}