public class LinkedListDeque<T>{

	private int size;
	private Node sentinel;

	private class Node{
		Node prev; 
		T item;
		Node next;

		Node(Node p, T i, Node n){
			this.prev = p;
			this.item = i;
			this.next = n;
		} 
	}

	/* Create an empty linked list deque*/
	public LinkedListDeque(){
		sentinel = new Node(null, null, null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
		this.size = 0;
	}

	/* Create a deep copy of other*/	
	public LinkedListDeque(LinkedListDeque<T> other){
		this.size = 0;
		sentinel = new Node(null, null, null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
		for (int i = 0; i < other.size(); i++){
			addLast((T)other.get(i));
		}
	}

	/*
	add and remove must not involve any looping or recursion.
	 */
	/* Add an item x to the front of the deque */
	public void addFirst(T x){
		Node newNode = new Node(sentinel, x, sentinel.next);
		sentinel.next.prev = newNode;
		sentinel.next = newNode;
		size += 1;
	}

	/* Add an item x to the back of the deque */
	public void addLast(T x){
		Node newNode = new Node(sentinel.prev, x, sentinel);
		sentinel.prev.next = newNode;
		sentinel.prev = newNode;
		size += 1;
	}

	/* Return true if deque is empty, false otherwise */
	public boolean isEmpty(){
		return size == 0;
	}

	/* (Constant time) Return the number of items in the deque*/
	public int size(){
		return size;
	}

	/* Print the items in the deque from first to last, separated by a space 
	 * Once all the items have been printed, print out a new line. */
	public void printDeque(){
		Node curr = sentinel.next;
		while (curr != sentinel){
			System.out.print(curr.item + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	/* Removes and returns the item at the front of the deque 
	 * If no such item exists, return null. */
	public T removeFirst(){
		if (isEmpty()){
			return null;
		}
		sentinel.next = sentinel.next.next;
		sentinel.next.next.prev = sentinel;
		size -= 1;
		return sentinel.next.item;
	}

	/* Removes and returns the item at the back of the deque.
	 * If no such item exists, returns null. */
	public T removeLast(){
		if (isEmpty()){
			return null;
		}
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.prev.next = sentinel;
		size -= 1;
		return sentinel.prev.item;
	}

	/* (Use iteration) Get the item at the given index. If no such item exists, returns null */
	public T get(int index){
		if (isEmpty() | index > size - 1){
			return null;
		}
		Node curr = sentinel.next;
		for (int i = 0; i < index; i++){
			curr = curr.next;
		}
		return curr.item;
	}

	/* (Use recursion) */
	public T getRecursive(int index){
		if (isEmpty() | index > size - 1){
			return null;
		}
		return getRecursiveHelper(sentinel.next, index);
	}

	private T getRecursiveHelper(Node curr, int index){
		if (index == 0){
			return curr.item;
		} else {
			curr = curr.next;
			return getRecursiveHelper(curr, index - 1);
		}
	}
}