package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTester {
	
	HashTable<Integer, String> hashTest = new HashTable<Integer, String>();
	
	@BeforeEach
	void setUp() throws Exception {
		hashTest.put(1, "cannon");
		hashTest.put(2, "tobes");
		hashTest.put(3, "eli");
		hashTest.put(4, "carter");
		hashTest.put(5, "truman");
		hashTest.put(6, "ben");

//		for(MapEntry<Integer, String> e : hashTest.entries())
//			System.out.println("Person " + e.getKey() + " has name " + e.getValue() + ".");
	}

	@Test
	void testPut() {
		hashTest.put(7, "jimmy");
		assertEquals(7, hashTest.size());
	}
	
	@Test
	void testPutDuplicate() {
		hashTest.put(3, "jack");
		assertEquals(6, hashTest.size());
	}
	
	@Test
	void testRemove() {
		hashTest.remove(4);
		assertEquals(5, hashTest.size());
	}
	
	@Test
	void testClear() {
		hashTest.clear();
		assertEquals(0, hashTest.size());
	}
	
	@Test
	void testContainsKey() {
		assertEquals(true, hashTest.containsKey(4));
	}
	
	@Test
	void testContainsValue() {
		assertEquals(true, hashTest.containsValue("tobes"));
	}
	
	@Test
	void testEntries() {
		ArrayList<String> entryList = new ArrayList<String>();
		entryList.add("cannon");
		entryList.add("tobes");
		entryList.add("eli");
		entryList.add("carter");
		entryList.add("truman");
		entryList.add("ben");
		assertEquals(entryList, hashTest.entries().toString());
	}
	
	@Test
	void testGet() {
		assertEquals("eli", hashTest.get(3));
	}
	
	@Test
	void testIsEmpty() {
		hashTest.clear();
		assertEquals(true, hashTest.isEmpty());
	}
	
	@Test
	void testSize() {
		assertEquals(6, hashTest.size());
	}
}
