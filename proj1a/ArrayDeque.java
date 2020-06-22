public class ArrayDeque<T>{

	private int size;
	private int nextFirst;
	private int nextLast;
	private T[] items;

	private static final int INIT_CAPACITY = 8;
	private static final int RFACTOR = 2;
	private static final double MIN_USAGE_RATIO = 0.25;


	/* Creates an empty array deque. */
	public ArrayDeque(){
		this.items = (T[]) new Object[INIT_CAPACITY];
		this.size = 0;
		this.nextFirst = 4;
		this.nextLast = 5;
	}

	/* Creates a deep copy of other*/
	public ArrayDeque(ArrayDeque other){
		this.items = (T[]) new Object[other.items.length];
		this.size = other.size;
		this.nextFirst = other.nextFirst;
		this.nextLast = other.nextLast;
		System.arraycopy(other.items, 0, items, 0, other.items.length);
	}

	// helper methods
	private int addOne(int i){
		return (i + 1) % items.length;
	}

	private int minusOne(int i){
		return (i - 1 + items.length) % items.length;
	}

	private boolean isFull(){
		return size == items.length;
	}

	private boolean isSparse(){
		boolean flag = false;
		if (items.length > 16){
			if (size/items.length < MIN_USAGE_RATIO){
				flag = true;
			}
		}
		return flag;
	}

	private void resize(int capacity){
		T[] cache = (T[])new Object[capacity];
		int oldIndex = addOne(nextFirst);
		for (int i = 0; i < this.size; i++){
			cache[i] = items[oldIndex];
			oldIndex = addOne(oldIndex);
		}
		this.items = cache;
		nextFirst = capacity - 1;
		nextLast = size;
	}

	// add and remove must take constant time, except during resizing operations.
	/* Adds an item of type T to the front of the deque */
	public void addFirst(T item){
		if (isFull()){
			resize(items.length * RFACTOR);
		}
		items[nextFirst] = item;
		nextFirst = minusOne(nextFirst);
		size += 1;
	}

	/* Adds an item of type T to the back of the deque */
	public void addLast(T item){
		if (isFull()){
			resize(items.length * RFACTOR);
		}
		items[nextLast] = item;
		nextLast = addOne(nextLast);
		size += 1;
	}

	/* Returns true if deque is empty, false otherwise */
	public boolean isEmpty(){
		return size == 0;
	}

	/* (constant time) Returns the number of items in the deque. */
	public int size(){
		return size;
	}

	/* Prints the items in the deque from first to last, separated by a space. 
	 * Once all the items have been printed, print out a new line.*/
	public void printDeque(){
		int index = addOne(nextFirst);
		for (int i = 0; i < size; i++){
			System.out.print(items[index] + " ");
			index = addOne(index);
		}
		System.out.println();
	}

	/* Removes and returns the item at the front of the deque.
	 * If no such item exists, returns null. */
	public T removeFirst(){
		if (isSparse()){
			resize(items.length/RFACTOR);
		}
		nextFirst = addOne(nextFirst);
		T firstItem = items[nextFirst];
		items[nextFirst] = null;
		size -= 1;
		return firstItem;
	}

	/* Removes and returns the item at the back of the deque.
	 * If no such item exists, returns null. */
	public T removeLast(){
		if (isSparse()){
			resize(items.length/RFACTOR);
		}
		nextLast = minusOne(nextLast);
		T lastItem = items[nextLast];
		items[nextLast] = null;
		size -= 1;
		return lastItem;
	}

	/* (constant time) Gets the item at the given index, where 0 is the front, 1 is the next item and so forth.
	 * If no such item exists, returns null. Nust not alter the deque. */
	public T get(int index){
		if (index > size - 1){
			return null;
		}
		int start = addOne(nextFirst);
		return items[(start + index) % items.length];
	}

}