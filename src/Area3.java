/**
 *  The Area3 class handles choosing the appropriate EL creatures to be shown in the third area, 
 *  which in this case are ALL creatures.
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1
 */
public class Area3 extends Area {

    /**
     * Constructs an Area3 object, initializing it with a specific size (4x4).
     */
    public Area3() {
        super(4, 4);
    }

    /**
     * Displays the layout of Area 3, marking the player's position with "P".
     * Overrides the method in the superclass (Area).
     */
    @Override
    public void displayArea() {
        System.out.println("Area 3:");
        super.displayArea();
    }

    /**
     * Handles the encounter of creatures in Area 3 based on their Evolution Level (EL).
     * Overrides the method in the superclass (Area).
     * 
     * @return The encountered creature, allowing EL1, EL2, and EL3 creatures.
     */
    @Override
    public Creature encounteredCreature() {
        System.out.println("You encountered a creature in Area 3:");

        // Adjust the logic to allow EL1, EL2, and EL3 creatures
        int el = random.nextInt(3) + 1; // Randomly choose between EL1, EL2, and EL3

        switch (el) {
            case 1:
                return encounterCreatureEL1();
            case 2:
                return encounterCreatureEL2();
            case 3:
                return encounterCreatureEL3();
            default:
                throw new IllegalStateException("Unexpected value: " + el);
        }
    }
}
