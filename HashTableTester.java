package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTester {
	
	HashTable<Integer, String> hashTest = new HashTable<Integer, String>();
	
	HashTable<String, String> newTest = new HashTable<String, String>();
	
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
		
		newTest.put("hello", "a");
        newTest.put("world", "b");
        newTest.put("cat", "c");
        newTest.put("dog", "d");
	}

	@Test
	void testNewRemove() {
		assertEquals("a", newTest.remove("hello"));
		assertEquals(3, newTest.size());
	}
	
	@Test
	void testReHash() {
		hashTest.put(10, "cannon");
		hashTest.put(20, "tobes");
		hashTest.put(30, "eli");
		hashTest.put(40, "carter");
		hashTest.put(50, "truman");
		hashTest.put(60, "ben");
		hashTest.put(100, "cannon");
		hashTest.put(200, "tobes");
		hashTest.put(300, "eli");
		hashTest.put(400, "carter");
		hashTest.put(500, "truman");
		hashTest.put(600, "ben");
		
		assertEquals(18, hashTest.size());
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
        int counter = 0;
        for(MapEntry<Integer, String> e : hashTest.entries()) {
            assertEquals(e.getValue(), entryList.get(counter));
            counter++;
        }
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
