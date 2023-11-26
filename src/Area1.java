public class Area1 extends Area {

    public Area1() {
        super(5, 1);
    }

    @Override
    public void displayArea() {
        System.out.println("Area 1:");
        super.displayArea();
    }

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

