package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V>{
	
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	private int capacity;
	private int size;
	private int lambda;
	
	public HashTable() {
		size = 0;
		capacity = 10;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < capacity; i++)
		   table.add(new LinkedList<MapEntry<K, V>>());
	}

	@Override
	public void clear() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < capacity; i++)
			   table.add(new LinkedList<MapEntry<K, V>>());
	}

	@Override
	public boolean containsKey(K key) {
		int index = Math.abs(key.hashCode()) % table.size();
		LinkedList<MapEntry<K, V>> currentList = table.get(index);
		
		int counter = 0;
		MapEntry<K, V> temp = currentList.get(counter);
		while(temp.getKey() != null) {
			if(temp.getKey() == key) {
				return true;
			}
			temp = currentList.get(counter++);
		}
		
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		for(int i=0; i<table.size(); i++) {
			for(int j=0; j<table.get(i).size(); j++) {
				if(table.get(i).get(j).getValue().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		ArrayList<MapEntry<K, V>> entryList = new ArrayList<MapEntry<K, V>>();
		for(int i=0; i<table.size(); i++) {
			for(int j=0; j<table.get(i).size(); j++) {
					entryList.add(table.get(i).get(j));
			}
		}
		return entryList;
	}

	@Override
	public V get(K key) {
		int index = Math.abs(key.hashCode()) % table.size();
		LinkedList<MapEntry<K, V>> currentList = table.get(index);
		
		int counter = 0;
		MapEntry<K, V> temp = currentList.get(counter);
		while(temp.getKey() != null) {
			if(temp.getKey() == key) {
				return temp.getValue();
			}
			temp = currentList.get(counter++);
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V put(K key, V value) {
		if(calculateLoadFactor()>5) {
			reHash();
		}
		int index = Math.abs(key.hashCode()) % table.size();
		MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
		MapEntry<K, V> oldEntry = new MapEntry<K, V>(key, value);
		//if key already exists in map
		if(containsKey(key) == true) {
			V oldValue = this.get(key);
			//loops through LinkedList
			for(int i = 0; i < table.get(index).size(); i++) {
				//changes oldEntry to always be current MapEntry
				oldEntry=table.get(index).get(i);
				if (oldEntry.getKey().equals(newEntry.getKey())) {
					oldEntry=newEntry;
				}
				size++;
				return oldValue;
			}
		}
		else {
			table.get(index).add(newEntry);
			size++;
		}
		return null;
	}

	@Override
	public V remove(K key) {
		int index = Math.abs(key.hashCode()) % table.size();
		MapEntry<K, V> currentEntry = new MapEntry<K, V>(null, null);
		if(containsKey(key) == true) {
			V oldValue = this.get(key);
			findAndRemoveLinkedList(key);
			return oldValue;
			}
		else {
			findAndRemoveLinkedList(key);
			return null;
		}
	}
	
	//helper method
	private void findAndRemoveLinkedList(K key) {
		int index = Math.abs(key.hashCode()) % table.size();
		MapEntry<K, V> currentEntry = new MapEntry<K, V>(null, null);
		for(int i = 0; i < table.get(index).size(); i++) {
			currentEntry=table.get(index).get(i);
			if (currentEntry.getKey().equals(currentEntry.getKey())) {
				//once found removes at index i in linked list
				table.get(index).remove(i);
			}
			size--;
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	//helper method to calculate lamda
	private double calculateLoadFactor() {
		double totalListLength=0;
		for(int i=0; i<table.size(); i++) {
			totalListLength+=table.get(i).size();
		}
		return totalListLength/table.size();
	}
	
	//helper method to rehash
	private void reHash() {
		ArrayList<LinkedList<MapEntry<K, V>>> tempTable = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < table.size()*2; i++) {
			tempTable.add(new LinkedList<MapEntry<K, V>>());
			
		//don't know where to go from here 
		}
	}
	
	
}