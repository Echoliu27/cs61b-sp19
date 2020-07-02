package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertTrue(arb.isFull());
        assertEquals(1, arb.dequeue());
        assertFalse(arb.isFull());
        assertEquals(2,arb.peek());
        arb.enqueue(4);
        assertTrue(arb.isFull());
    }
}
