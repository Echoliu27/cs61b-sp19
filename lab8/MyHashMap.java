import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

import static java.lang.Math.*;

public class MyHashMap<K, V> implements Map61B<K, V>{

	private class Entry{
		K key;
		V value;
		Entry(K key, V value){
			this.key = key;
			this.value = value;
		}
	}

	private HashSet<K> keySet;
	private ArrayList<ArrayList<Entry>> buckets;
	private int numOfEntries = 0;
	private int numOfBuckets;
	private final double maxAvgBucketSize;

	public MyHashMap(){
		this(16); // 'this' keyword can be used inside the constructor to call another overloaded constructor in the same class
	}
	public MyHashMap(int initialSize){
		this(initialSize, 0.75);
	}
	public MyHashMap(int initialSize, double loadFactor){
		keySet = new HashSet<>();
		buckets = new ArrayList<>();
		numOfBuckets = initialSize;
		maxAvgBucketSize = loadFactor;
		for (int i = 0; i < numOfBuckets; i++){
			buckets.add(new ArrayList<>());
		}
	}

	/** Removes all of the mappings from this map. */
	@Override
	public void clear(){
		keySet = new HashSet<>();
		buckets = new ArrayList<>();
		numOfEntries = 0;
		numOfBuckets = 0;
	}

	/** Returns true if this map contains a mapping for the specified key. */
	@Override
	public boolean containsKey(K key){
		return keySet.contains(key);
	}

	private int hash(K key, int capacity){
		return floorMod(key.hashCode(), capacity); // every object in java should have a hashcode method
	}

	/**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
	public V get(K key){
		if (!containsKey(key)){
			return null;
		}
		int index = hash(key, numOfBuckets);
		for (Entry entry : buckets.get(index)){
			if (entry.key.equals(key)){
				return entry.value;
			}
		}
		return null;
	}

	/** Returns the number of key-value mappings in this map. */
	@Override
	public int size(){
		return numOfEntries;
	}

	/**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
	public void put(K key, V value){

		// if bucket has the key already
		if (containsKey(key)){
			update(key,value);
			return;
		}

		// consider loadfactor
		if (numOfEntries > maxAvgBucketSize * numOfBuckets){
			resize( 2 * numOfBuckets );
		}
		
		// save the new entry
		int index = hash(key, numOfBuckets);
		buckets.get(index).add(new Entry(key,value));
		keySet.add(key);
		numOfEntries += 1;
	}


	private void update(K key, V value){
		int index = hash(key, numOfBuckets);
		for (Entry entry : buckets.get(index)){
			if (entry.key.equals(key)){
				entry.value = value;
			}
		}
	}

	private void resize(int capacity){

		// create new empty bucket list
		ArrayList<ArrayList<Entry>> newBuckets = new ArrayList<>();
		for (int i = 0; i < capacity; i++){
				newBuckets.add(new ArrayList<>());
		}

		// copy paste the original entries into the new list
		for (K key : keySet){
			int index = hash(key, capacity);
			newBuckets.get(index).add(new Entry(key, get(key)));
		}
		this.numOfBuckets = capacity;
		this.buckets = newBuckets;
	}

	/** Returns a Set view of the keys contained in this map. */
	@Override
    public Set<K> keySet(){
    	return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){
    	//throw new UnsupportedOperationException();
    	if (!containsKey(key)){
    		return null;
    	} else {
    		return remove(key, get(key));
		}
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
    	//throw new UnsupportedOperationException();
    	if (!containsKey(key)) {
            return null;
        }
    	keySet.remove(key);
    	numOfEntries -= 1;
    	int index = hash(key, numOfBuckets);
		buckets.get(index).remove(Entry(key, value));
		return value;
    }

    public Iterator<K> iterator(){
    	return keySet.iterator();
    }
}