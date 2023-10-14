/**
 *  @code Creature class represents the creatures in the game ( obviously ) and stores their own attributes
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.1
 */


public class Creature
{
    private String name, type, family;
    private int evolutionLevel, health;   

    
    /**
     * 
     * @param name name of the creature, obviously
     * @param type type of the creature, obviously
     * @param family family of the creature, where it belongs to
     * @param evolutionLevel the evolution level of the creature, ranging from 1 to 3
     * @param health the health of the creature
     */ // Constructor
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
    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public String getFamily()
    {
        return family;
    }

    public int getEvolutionLevel()
    {
        return evolutionLevel;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    // toString() method returns string representation of object
    public String toString() // we use this to display the creature infromation
    {
        return "Name: " + name + "\nType " + type + "\nFamily: " + family + "\nEvolution: " + evolutionLevel +
                "\nHealth: " + health;
    }
}