package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    // Return size of the buffer
    public int capacity();
    //Return number of items currently in the buffer
    public int fillCount();
    //Add item x to the end
    public void enqueue(T x);
    //Delete and return item from the front
    public T dequeue();
    //Return (but do not delete) item from the front
    public T peek();
    // Is the buffer empty
    public default boolean isEmpty(){
        return fillCount() == 0;
    }
    //Is the buffer full
    public default boolean isFull(){
        return capacity() == fillCount();
    };

    // Add iterator method
    public Iterator<T> iterator();
}
