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
		int index = Math.abs(key.hashCode()) % table.size();
		V temp;
		if(containsKey(key) == true) {
			temp = this.get(key);
			for(int i = 0; i < table.get(index).size(); i++) {
				if(table.get(index).get(i).getKey() == key) {
					V valueInMap = table.get(index).get(i).getValue();
					valueInMap = value;
					return temp;
				}
			}
		}
		MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
		
		
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	
}