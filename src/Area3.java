public class Area3 extends Area {

    public Area3() {
        super(4, 4);
    }

    @Override
    public void displayArea() {
        System.out.println("Area 3:");
        super.displayArea();
    }


    @Override
    public Creature encounteredCreature() {
        System.out.println("You encountered a creature in Area 2:");
        
        // Adjust the logic to only allow EL1 and EL2 and EL3 creatures
        int el = random.nextInt(3) + 1; // Randomly choose between EL1 and EL2 and EL3

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
