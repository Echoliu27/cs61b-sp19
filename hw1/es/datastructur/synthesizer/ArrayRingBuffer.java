package es.datastructur.synthesizer;
import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayRingBuffer<T>  implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()){
            throw new RuntimeException("Ring buffer overflow.");
        }
        rb[last] = x;
        last ++;
        fillCount ++;
        if (last == capacity()){
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T oldItem = rb[first];
        rb[first] = null;
        first += 1;
        fillCount --;
        if (first == capacity()){
            first = 0;
        }
        return oldItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T oldItem = rb[first];
        return oldItem;
    }

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    // Support iteration and equals
    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }
    private class ArrayRingBufferIterator implements Iterator<T>{
        private int count;
        private int pos;

        public ArrayRingBufferIterator(){
            count = 0;
            pos = first;
        }

        public boolean hasNext(){
            return count < fillCount();
        }

        public T next(){
            T item = rb[pos];
            pos ++;
            if (pos == capacity()){
                pos = 0;
            }
            count += 1;
            return item;
        }
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (other == null){
            return false;
        }
        if (other.getClass() != this.getClass()){
            return false;
        }

        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (this.fillCount() != o.fillCount()){
            return false;
        }
        Iterator<T> thisIter = this.iterator();
        Iterator<T> otherIter = o.iterator();
        while (thisIter.hasNext() && otherIter.hasNext()){
            if (!thisIter.next().equals(otherIter.next())){
                return false;
            }
        }
        return true;

    }
}
