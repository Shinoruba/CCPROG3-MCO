/**
 *  @code Creature class represents the creatures in the game ( obviously ) and stores their own attributes
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.0
 */


public class Creature
{
    private String name, type, family;
    private int evolutionLevel, health;   

    // Constructor
    /**
     * 
     * @param name name of the creature, obviously
     * @param type type of the creature, obviously
     * @param family family of the creature, where it belongs to
     * @param evolutionLevel the evolution level of the creature, ranging from 1 to 3
     * @param health the health of the creature
     */
    public Creature(String name, String type, String family, int evolutionLevel, int health)
    {
        this.name=name;
        this.type=type;
        this.family=family;
        this.evolutionLevel=evolutionLevel;
        this.health=100; // Idk if its 100
    }
}

    // Methods ( to be implemented )
