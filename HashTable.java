package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V>{
	
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	private int capacity;
	private int size;
	private double lambda;
	
	public HashTable() {
		size = 0;
		capacity = 10;
		lambda = 0;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < capacity; i++)
		   table.add(new LinkedList<MapEntry<K, V>>());
	}

	@Override
	public void clear() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < capacity; i++)
			   table.add(new LinkedList<MapEntry<K, V>>());
		size = 0;
	}

	@Override
	public boolean containsKey(K key) {
		if(size==0) {
			return false;
		}
		int index = Math.abs(key.hashCode()) % table.size();
		LinkedList<MapEntry<K, V>> currentList = table.get(index);
		MapEntry<K, V> temp = new MapEntry<K, V>(null, null);
		for(int i = 0; i<currentList.size(); i++) {
			temp = currentList.get(i);
			if(temp.getKey() == key) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		if(size==0) {
			return false;
		}
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
		MapEntry<K, V> temp = new MapEntry<K, V>(null, null);
		for(int i = 0; i<currentList.size(); i++) {
			temp = currentList.get(i);
			if(temp.getKey() == key) {
				return temp.getValue();
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V put(K key, V value) {
		if(lambda>5) {
			reHash();
		}
		int index = Math.abs(key.hashCode()) % table.size();
		MapEntry<K, V> oldEntry = new MapEntry<K, V>(null, null);
		//if key already exists in map
		if(containsKey(key) == true) {
			V oldValue = this.get(key);
			//loops through LinkedList
			for(int i = 0; i < table.get(index).size(); i++) {
				//changes oldEntry to always be current MapEntry
				oldEntry=table.get(index).get(i);
				if (oldEntry.getKey().equals(key)) {
					oldEntry.setValue(value);
					return oldValue;
				}
			}
		}
		else {
			MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
			table.get(index).add(newEntry);
			size++;
			calculateLoadFactor();
		}
		return null;
	}

	@Override
	public V remove(K key) {
		if(containsKey(key) == true) {
			V oldValue = this.get(key);
			findAndRemoveLinkedList(key);
			return oldValue;
			}
		else {
			return null;
		}
	}
	
	//helper method
	private void findAndRemoveLinkedList(K key) {
		int index = Math.abs(key.hashCode()) % table.size();
		MapEntry<K, V> currentEntry = new MapEntry<K, V>(null, null);
		for(int i = 0; i < table.get(index).size(); i++) {
			currentEntry=table.get(index).get(i);
			if (currentEntry.getKey().equals(key)) {
				//once found removes at index i in linked list
				table.get(index).remove(i);
				size--;
				calculateLoadFactor();
			}
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	//helper method to calculate lambda
	private void calculateLoadFactor() {
		double totalListLength=0;
		for(int i=0; i<table.size(); i++) {
			totalListLength+=table.get(i).size();
		}
		lambda = totalListLength/table.size();
	}
	
	//helper method to rehash
	private void reHash() {
		capacity = table.size()*2;
		List<MapEntry<K, V> >entryList = entries();
		clear();
		lambda = 0;
		for(MapEntry<K, V> m: entryList) {
			put(m.getKey(),m.getValue());
		}
		calculateLoadFactor();
	}
	
	
}