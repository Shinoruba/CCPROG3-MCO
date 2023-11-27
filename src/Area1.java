/**
 *  The Area1 class handles choosing the appropriate EL creatures to be shown in the first area, 
 *  which in this case are only EL1 creatures.
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1
 */
public class Area1 extends Area {

    /**
     * Constructs an Area1 object, initializing it with a specific size (5x1).
     * @param j
     * @param i
     */
    public Area1(int i, int j) {
        super(5, 1);
    }

    /**
     * Displays the layout of Area 1, marking the player's position with "P".
     * Overrides the method in the superclass (Area).
     */
    @Override
    public void displayArea() {
        System.out.println("Area 1:");
        super.displayArea();
    }

    /**
     * Handles the encounter of creatures in Area 1 based on their Evolution Level (EL).
     * Overrides the method in the superclass (Area).
     * 
     * @return The encountered creature limited to EL1 creatures.
     */
    @Override
    public Creature encounteredCreature() {
        System.out.println("You encountered a creature in Area 1:");

        // Adjust the logic to only allow EL1 creatures
        int el = random.nextInt(1) + 1; // bruh

        switch (el) {
            case 1:
                return encounterCreatureEL1();
            default:
                throw new IllegalStateException("Unexpected value: " + el);
        }
    }
}

