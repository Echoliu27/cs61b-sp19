import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
	private Node root; //root of BST

	private class Node{
		private K key;
		private V value;
		private Node left, right; //left and right subtrees
		private int size;     // number of nodes in subtree

		public Node(K key, V value){
			this.key = key;
			this.value = value;
			this.size = 1;
		}
	}

	// Removes all of the mappings from this map
	@Override
	public void clear(){
		root = null;
	}

	// Returns true if this map contains a mapping for the specified key
	@Override
	public boolean containsKey(K key){
		return get(key) != null;
	}

	// Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key
	@Override
	public V get(K key){
		return get(root, key);
	}

	public V get(Node x, K key){
		if (x == null){
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0){
			return get(x.left, key);
		} else if (cmp > 0){
			return get(x.right, key);
		} else {
			return x.value;
		}
	}

	// Returns the number of key-value mappings in this map.
	@Override
	public int size(){
		return size(root);
	}

	public int size(Node x){
		if (x == null){
			return 0;
		}
		return x.size;
	}

	@Override
	public void put(K key, V value){
		root = put(root, key, value);
	}

	public Node put(Node x, K key, V value){
		if (x == null){
			return new Node(key, value);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0){
			x.left = put(x.left, key, value);
		} else if (cmp > 0){
			x.right = put(x.right, key, value);
		} else {
			x.value = value;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	@Override
	public Set<K> keySet(){
		throw new UnsupportedOperationException();
	}

	@Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    // Print out BSTMap in order of increasing key
    public void printInOrder(){
    	printInOrder(root);
    }

    public void printInOrder(Node x){
    	if (x != null){
    		printInOrder(x.left);
    		System.out.println(x.key + ": " + x.value);
    		printInOrder(x.right);
    	}
    }

    
}