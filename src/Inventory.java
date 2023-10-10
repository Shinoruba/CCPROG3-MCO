
/**
 *  @code Inventory class manages the user's collection of creatures
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.0
 */

import java.util.*; // too lazy, might as well get everything lmao
public class Inventory 
{
    private ArrayList<Creature> creatures;  // stores creatures
    private Creature activeCreature;        // represents the current active creature

    public Inventory()
    {
        creatures = new ArrayList<>();
        activeCreature = null;              // sett active creature to null for now
    }

        public void addCreature(Creature creature)
        {
            creatures.add(creature);        // uses .add() method to add creature to arraylist
            if(creature == activeCreature)
            {
                setActiveCreature(null);
            }
        }

        public void removeCreature(Creature creature)
        {
            creatures.remove(creature);     // uses .remove() method to remove creature from arraylist, similar to it's .add() method
            if(creature == activeCreature)
            {
                setActiveCreature(null);
            }
        }

    // retrieve the active creature
    public Creature getActiveCreature()
    {
        return activeCreature;
    }


    // sets the active creature
    public void setActiveCreature(Creature creature)
    {
        if(creatures.contains(creature))
        {
            activeCreature = creature;
        }
    }

    // returns copy of inventory list
    public ArrayList<Creature> getAllCreatures() 
    {
        return new ArrayList<>(creatures);
    }

    // returns number of creatures from inventory
    public int getSize()
    {
        return creatures.size();
    }
}
