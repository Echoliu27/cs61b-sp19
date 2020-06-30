package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus{
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(2);
        Plip p = new Plip();
        c.attack(p);
        assertEquals(3, c.energy(),0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus bc = c.replicate();
        assertEquals(1, bc.energy(),0.01);
        Clorus bc2 = bc.replicate();
        assertEquals(0.5, bc2.energy(), 0.01);
        Clorus bc3 = bc2.replicate();
        assertEquals(0.25, bc3.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Plip nearby, attack one of them randomly
        p = new Clorus(2);
        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(Direction.TOP, new Plip(1));
        topPlip.put(Direction.BOTTOM, new Empty());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy < 1; stay.
        p = new Clorus(.99);

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE,Direction.TOP);
        assertEquals(expected, actual);

    }
}