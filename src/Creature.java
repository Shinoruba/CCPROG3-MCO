/**
 *  Creature class represents the creatures in the game ( obviously ) and stores their own attributes
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.2
 */


public class Creature
{
    private String name, type, family;
    private int evolutionLevel, health;   

    
    /**
     * Constructs a Creature with the given attributes.
     *
     * @param name           The name of the creature.
     * @param type           The type of the creature.
     * @param family         The family to which the creature belongs.
     * @param evolutionLevel The evolution level of the creature (1 to 3).
     * @param health         The health of the creature.
     */
    public Creature(String name, String type, String family, int evolutionLevel, int health)
    {
        this.name=name;
        this.type=type;
        this.family=family;
       
        // Condition to make sure EL is correctly initialized
        if(evolutionLevel >= 1 && evolutionLevel <= 3)
        {
            this.evolutionLevel=evolutionLevel;
        } 
        else           
        {
            this.evolutionLevel=1;
        }
        this.health=health;
    }

    public Creature(String name, String type, String family, int evolutionLevel) 
    {
        this(name, type, family, evolutionLevel, 100); // Default HP is set to 100
    }


// =================================================================        
            // Methods ( must i explain these lol )
    /**
     * Retrieves the name of the creature.
     *
     * @return The name of the creature.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Retrieves the type of the creature.
     *
     * @return The type of the creature.
     */
    public String getType()
    {
        return type;
    }

    /**
     * Retrieves the family of the creature.
     *
     * @return The family of the creature.
     */
    public String getFamily()
    {
        return family;
    }

    /**
     * Retrieves the evolution level of the creature.
     *
     * @return The evolution level of the creature.
     */
    public int getEvolutionLevel()
    {
        return evolutionLevel;
    }

    /**
     * Retrieves the health of the creature.
     *
     * @return The health of the creature.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets the health of the creature.
     *
     * @param health The new health value to set.
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Returns a string representation of the creature's attributes.
     *
     * @return A string representing the creature's name, type, family, evolution level, and health.
     */
    public String toString()
    {
        return "Name: " + name + "\nType " + type + "\nFamily: " + family + "\nEvolution: " + evolutionLevel +
                "\nHealth: " + health;
    }
}