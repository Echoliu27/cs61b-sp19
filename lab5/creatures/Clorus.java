package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.random;
import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    /**
     * fraction of energy to bestow upon offspring.
     */
    private double repEnergyRetained = 0.5;

    public Clorus(double e){
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /* Default constructor */
    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return new Color(r, g, b);
    }

    public void move(){
        energy -= 0.03;
//        if (energy < 0){
//            energy = 0;
//        }
    }

    public void attack(Creature c){
        energy += c.energy();
    }

    public void stay(){
        energy -= 0.01;
//        if (energy < 0){
//            energy = 0;
//        }
    }

    public Clorus replicate(){
        energy = repEnergyRetained * energy;
        double babyEnergy = energy;
        return new Clorus(babyEnergy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors){
        Deque<Direction> emptyNeighbors = new ArrayDeque<Direction> ();
        Deque<Direction> plipNeighbors = new ArrayDeque<Direction> ();
        boolean anyPlip = false;

        for (Direction d: neighbors.keySet()){
            if (neighbors.get(d).name().equals("empty")){
                emptyNeighbors.addLast(d);
            } else if (neighbors.get(d).name().equals("plip")){
                anyPlip = true;
                plipNeighbors.addLast(d);
            }
        }

        // Rule 1
        if (emptyNeighbors.size() == 0){
            return new Action(Action.ActionType.STAY);
        // Rule 2
        } else if (anyPlip){
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        // Rule 3
        } else if (energy >= 1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        // Rule 4
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }

    }


}