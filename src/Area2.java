
public class Area2 extends Area {

    public Area2() {
        super(3, 3);
    }

    @Override
    public void displayArea() {
        System.out.println("Area 2:");
        super.displayArea();
    }
 

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
