/**
 *  The Area2 class handles choosing the appropriate EL creatures to be shown in the second area, 
 *  which in this case are only EL1 & EL2 creatures.
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1
 */
public class Area2 extends Area {

    /**
     * Constructs an Area2 object, initializing it with a specific size (3x3).
     * @param j
     * @param i
     */
    public Area2(int i, int j) {
        super(3, 3);
    }

    /**
     * Displays the layout of Area 2, marking the player's position with "P".
     * Overrides the method in the superclass (Area).
     */
    @Override
    public void displayArea() {
        System.out.println("Area 2:");
        super.displayArea();
    }

    /**
     * Handles the encounter of creatures in Area 2 based on their Evolution Level (EL).
     * Overrides the method in the superclass (Area).
     * 
     * @return The encountered creature, limited to EL1 and EL2 creatures.
     */
    @Override
    public Creature encounteredCreature() {
        System.out.println("You encountered a creature in Area 2:");

        // Adjust the logic to only allow EL1 and EL2 creatures
        int el = random.nextInt(2) + 1; // Randomly choose between EL1 and EL2

        switch (el) {
            case 1:
                return encounterCreatureEL1();
            case 2:
                return encounterCreatureEL2();
            default:
                throw new IllegalStateException("Unexpected value: " + el);
        }
    }
}
