import java.util.*;
/**
 *  The Inventory class manages the user's collection of creatures.
 *  Version 2.0: Added the ability to evolve creature
 * 
 *  Model-View-Controller (MVC) pattern: MODEL = Represents the data and business logic related to the player's inventory.
 * 
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 2.1
 */
public class Inventory 
{
    private final ArrayList<Creature> creatures;  // stores creatures
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
        activeCreature = null;              // set the active creature to null for now
    }

    /**
     * Adds a creature to the inventory.
     *
     * @param creature The creature to add to the inventory.
     * @throws IllegalArgumentException if creature is null.
     */
    public void addCreature(Creature creature) {
        if (creature != null) {
            creatures.add(creature);
            if (creature == activeCreature) {
                setActiveCreature(creature);
            }
        } else {
            throw new IllegalArgumentException("Creature cannot be null.");
        }
    }

    /**
     * Removes a creature from the inventory.
     *
     * @param creature The creature to remove from the inventory.
     * @throws IllegalArgumentException if creature is null.
     */
    public void removeCreature(Creature creature) {
        if (creature != null) {
            creatures.remove(creature);
            if (creature == activeCreature) {
                setActiveCreature(null);
            }
        } else {
            throw new IllegalArgumentException("Creature cannot be null.");
        }
    }

    /**
     * Retrieves the active creature in the inventory.
     *
     * @return The active creature or null if none is active.
     */
    public Creature getActiveCreature() {
        return activeCreature;
    }

    /**
     * Sets the active creature in the inventory.
     *
     * @param creature The creature to set as active.
     */
    public void setActiveCreature(Creature creature) {
        if (creatures.contains(creature)) {
            activeCreature = creature;
        }
    }

    /**
     * Returns an unmodifiable view of the list of all creatures in the inventory.
     *
     * @return An unmodifiable list containing all creatures in the inventory.
     */
    public List<Creature> getAllCreatures() {
        return Collections.unmodifiableList(new ArrayList<>(creatures));
    }

    /**
     * Returns the number of creatures in the inventory.
     *
     * @return The number of creatures in the inventory.
     */
    public int getSize() {
        return creatures.size();
    }



//========================================================================
    // Evolution methods:


/**
 * Attempts to evolve two creatures from the user's inventory.
 * Evolution is successful if the selected creatures have the same EL and family.
 * Upon success, the selected creatures are removed, and the evolved creature is added.
 * The new creature has the next EL of the same family.
 *
 * @param index1 The index of the first creature in the inventory.
 * @param index2 The index of the second creature in the inventory.
 * @return True if evolution is successful, false otherwise.
 */
public boolean evolveCreatures(int index1, int index2) {
    if (index1 < 0 || index1 >= creatures.size() || index2 < 0 || index2 >= creatures.size()) {
        System.out.println("Invalid creature indices.");
        return false;
    }

    Creature creature1 = creatures.get(index1);
    Creature creature2 = creatures.get(index2);

    // Check if the creatures are eligible for evolution
    if (creature1.getEvolutionLevel() == creature2.getEvolutionLevel() &&
        creature1.getFamily().equals(creature2.getFamily()) &&
        creature1.getEvolutionLevel() < 3 &&
        !creature1.equals(creature2)) {

        // Evolution successful
        System.out.println("Evolution SUCCESS!");

        // Determine the evolved creature's name based on family and evolution level
        String evolvedName;
        if (creature1.getEvolutionLevel() == 1) {
            evolvedName = determineEvolvedNameEL2(creature1);
        } else {
            evolvedName = determineEvolvedNameEL3(creature1);
        }

        // Create the evolved creature
        Creature evolvedCreature = new Creature(
                evolvedName,
                creature1.getType(),
                creature1.getFamily(),
                creature1.getEvolutionLevel() + 1,
                creature1.getHealth() + creature2.getHealth()
        );

        // Remove the selected creatures from the inventory
        creatures.remove(creature1);
        creatures.remove(creature2);

        // Add the evolved creature to the end of the inventory
        creatures.add(evolvedCreature);

        // Set the evolved creature as the active creature
        setActiveCreature(evolvedCreature);

        return true;
    } else {
        // Evolution failed
        System.out.println("Evolution FAILS! Selected creatures are not eligible for evolution.");
        return false;
    }
}

    /**
     * Displays the evolution screen.
     * Shows selected creatures' images, names, and EL.
     * Shows evolution prompts (SUCCESS or FAIL).
     * Shows the evolved creature upon successful evolution.
     *
     * @param index1 The index of the first creature in the inventory.
     * @param index2 The index of the second creature in the inventory.
     */
    public void displayEvolutionScreen(int index1, int index2) {
        if (index1 < 0 || index1 >= creatures.size() || index2 < 0 || index2 >= creatures.size()) {
            System.out.println("Invalid creature indices.");
            return;
        }

        Creature creature1 = creatures.get(index1);
        Creature creature2 = creatures.get(index2);

        System.out.println("Evolution Screen:");
        System.out.println("[1] " + creature1.getName() + " (EL" + creature1.getEvolutionLevel() + ")");
        System.out.println("[2] " + creature2.getName() + " (EL" + creature2.getEvolutionLevel() + ")");
        System.out.println("---");

        // Attempt to evolve creatures
        boolean evolutionSuccess = evolveCreatures(index1, index2);

        // Display evolution prompts
        if (evolutionSuccess) {
            System.out.println("---");
            System.out.println("Evolved Creature: " + getActiveCreature());
        } else {
            System.out.println("Evolution FAILS! Selected creatures are not eligible for evolution.");
        }

        System.out.println("=============");
    }

 /**
 * Determines the name of the evolved creature for Evolution Level 2 (EL2) based on family.
 *
 * @param creature The creature to evolve.
 * @return The name of the evolved creature for EL2.
 */
private String determineEvolvedNameEL2(Creature creature) {
    String creatureFamily = creature.getFamily();
    switch (creatureFamily) {
        case "Family A": // Fire
            return "Strawleon";
        case "Family B": // Fire
            return "Chocofluff";
        case "Family C": // Fire
            return "Parfure";
        case "Family D": // Grass
            return "Chocosaur";
        case "Family E": // Grass
            return "Golberry";
        case "Family F": // Grass
            return "Kirlikake";
        case "Family G": // Water
            return "Tartortle";
        case "Family H": // Water
            return "Chocolish";
        case "Family I": // Water
            return "Dewice";
        case "Family J": // Professor
            return "Super Nicdao";
        default:
            return "Unknown";
    }
}

/**
 * Determines the name of the evolved creature for Evolution Level 3 (EL3) based on family.
 *
 * @param creature The creature to evolve.
 * @return The name of the evolved creature for EL3.
 */
private String determineEvolvedNameEL3(Creature creature) {
    String creatureFamily = creature.getFamily();
    switch (creatureFamily) {
        case "Family A": // Fire
            return "Strawizard";
        case "Family B": // Fire
            return "Candaros";
        case "Family C": // Fire
            return "Parfelure";
        case "Family D": // Grass
            return "Fudgasaur";
        case "Family E": // Grass
            return "Croberry";
        case "Family F": // Grass
            return "Velvevoir";
        case "Family G": // Water
            return "Piestoise";
        case "Family H": // Water
            return "Icesundae";
        case "Family I": // Water
            return "Samurcone";
        case "Family J": // Professor
            return "Ultimate Nicdao";
        default:
            return "Unknown";
    }
}
}
