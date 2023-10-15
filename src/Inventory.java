import java.util.*; // too lazy, might as well get everything lmao
/**
 *  The Inventory class manages the user's collection of creatures
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.2
 */
public class Inventory 
{
    private ArrayList<Creature> creatures;  // stores creatures
    private Creature activeCreature;        // represents the current active creature

    /**
     * Initializes a new inventory.
     * 
     * This constructor creates a new inventory for the user to store creatures. It initializes an empty list
     * of creatures and sets the active creature to null.
     */
    public Inventory()
    {
        creatures = new ArrayList<>();
        activeCreature = null;              // set active creature to null for now
    }

        /**
         * Adds a creature to the inventory.
         *
         * @param creature The creature to add to the inventory.
         */
        public void addCreature(Creature creature)
        {
            creatures.add(creature);        // uses .add() method to add creature to arraylist
            if(creature == activeCreature)
            {
                setActiveCreature(creature);
            }
        }

        /**
         * Removes a creature from the inventory.
         *
         * @param creature The creature to remove from the inventory.
         */
        public void removeCreature(Creature creature)
        {
            creatures.remove(creature);     // uses .remove() method to remove creature from arraylist, similar to it's .add() method
            if(creature == activeCreature)
            {
                setActiveCreature(null);
            }
        }

    /**
     * Retrieves the active creature in the inventory.
     *
     * @return The active creature or null if none is active.
     */
    public Creature getActiveCreature()
    {
        return activeCreature;
    }


    /**
     * Sets the active creature in the inventory.
     *
     * @param creature The creature to set as active.
     */
    public void setActiveCreature(Creature creature)
    {
        if(creatures.contains(creature))
        {
            activeCreature = creature;
        }
    }

    /**
     * Returns a copy of the list of all creatures in the inventory.
     *
     * @return A new ArrayList containing all creatures in the inventory.
     */
    public ArrayList<Creature> getAllCreatures() 
    {
        return new ArrayList<>(creatures);
    }

    /**
     * Returns the number of creatures in the inventory.
     *
     * @return The number of creatures in the inventory.
     */
    public int getSize()
    {
        return creatures.size();
    }
}