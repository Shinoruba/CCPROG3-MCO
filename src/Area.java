import java.util.*;

/**
 *  The Area class handles area exploration done by the user.
 *  Version 2.0: Area is the superclass of Area(1,2,3).
 * 
 *  Model-View-Controller (MVC) pattern: MODEL = Represents the data and logic related to the 3 different areas.
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 2
 */
public class Area
{
   private int[][] tiles; // 2D array of area's tiles
   private int currentX, currentY; // Current X and Y position 
   private int i, j; // Indexes of tiles
   protected Random random; // to be used in Area2 its pissing me off


    /**
     * Constructs an Area with the given number of rows and columns.
     *
     * @param nRows The number of rows in the area.
     * @param nCols The number of columns in the area.
     */
    public Area(int nRows, int nCols)
    {
        tiles = new int[nRows][nCols];
        currentX = 0; // starting X position
        currentY = 0; // starting Y position
        random = new Random();

        for(i=0; i<nRows; i++)
        {
            for(j=0; j<nCols; j++)
            {
                tiles[i][j]=0;
            }
        }
    }

            
    
// ==================================================================
            /**
             * Retrieves the current X position in the area.
             *
             * @return The current X position.
             */
            public int getCurrentX() 
            {
                return currentX;
            }

            /**
             * Sets the current X position in the area.
             *
             * @param currentX The new current X position to set.
             */
            public void setCurrentX(int currentX) 
            {
                this.currentX = currentX;
            }
        
            /**
             * Retrieves the current Y position in the area.
             *
             * @return The current Y position.
             */
            public int getCurrentY() 
            {
                return currentY;
            }
            
            /**
             * Sets the current Y position in the area.
             *
             * @param currentY The new current Y position to set.
             */
            public void setCurrentY(int currentY) 
            {
                this.currentY = currentY;
            }

            /**
             * Retrieves the 2D array of area tiles.
             *
             * @return The 2D array of area tiles.
             */
            public int[][] getTiles() 
            {
                return tiles;
            }

// =================================================================   
            /**
             * Displays the current area layout, marking the player's position with "P".
             */
            public void displayArea() // Used in exploreArea() method
            {              
                System.out.println("Current Area:");

                for (int i = 0; i < tiles.length; i++) 
                {
                    for (int j = 0; j < tiles[i].length; j++) 
                    {
                        if (i == currentX && j == currentY) 
                        {
                            System.out.print("P "); // Player's position
                        } 
                        else 
                        {
                            System.out.print(". "); // Empty tile
                        }
                    }
                    System.out.println(); // Move to the next row
                }
            }    
 
        /**
         * Determines whether the player should encounter a creature in the area.
         *
         * @return True if there is a chance to encounter a creature, false otherwise.
         */    
        public boolean shouldEncounterCreature() // Used in exploreArea() method
        {
            int encounterChanceThreshold = 40;
            return random.nextInt(100) < encounterChanceThreshold; // 40% chance to encounter a creature
        }

    /**
     * Handles the encounter of creatures in the area based on their Evolution Level (EL).
     *
     * @return The encountered creature.
     */
    public Creature encounteredCreature() {
        int el = random.nextInt(3) + 1;

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

        protected Creature encounterCreatureEL1() {
            List<Creature> possibleCreaturesEL1 = Arrays.asList(
                    new Creature("Strawander", "Fire", "Family A", 1, 50),
                    new Creature("Chocowool", "Fire", "Family B", 1, 50),
                    new Creature("Parfwit", "Fire", "Family C", 1, 50),
                    new Creature("Brownisaur", "Grass", "Family D", 1, 50),
                    new Creature("Frubat", "Grass", "Family E", 1, 50),
                    new Creature("Malts", "Grass", "Family F", 1, 50),
                    new Creature("Squirpie", "Water", "Family G", 1, 50),
                    new Creature("Chocolite", "Water", "Family H", 1, 50),
                    new Creature("Oshacone", "Water", "Family I", 1, 50),
                    new Creature("Nicdao", "Professor", "Family W", 1, 50)
            );
    
            return getRandomCreature(possibleCreaturesEL1);
        }
    
        protected Creature encounterCreatureEL2() {
            List<Creature> possibleCreaturesEL2 = Arrays.asList(
                    new Creature("Strawleon", "Fire", "Family A", 2, 60),
                    new Creature("Chocofluff", "Fire", "Family B", 2, 60),
                    new Creature("Parfure", "Fire", "Family C", 2, 60),
                    new Creature("Chocosaur", "Grass", "Family D", 2, 60),
                    new Creature("Golberry", "Grass", "Family E", 2, 60),
                    new Creature("Kirliecake", "Grass", "Family F", 2, 60),
                    new Creature("Tartortle", "Water", "Family G", 2, 60),
                    new Creature("Chocolish", "Water", "Family H", 2, 60),
                    new Creature("Dewice", "Water", "Family I", 2, 60),
                    new Creature("Super Nicdao", "Professor", "Family W", 2, 500)
            );
    
            return getRandomCreature(possibleCreaturesEL2);
        }
    
        protected Creature encounterCreatureEL3() {
            List<Creature> possibleCreaturesEL3 = Arrays.asList(
                    new Creature("Strawizard", "Fire", "Family A", 3, 70),
                    new Creature("Candaros", "Fire", "Family B", 3, 70),
                    new Creature("Parfelure", "Fire", "Family C", 3, 70),
                    new Creature("Fudgansaur", "Grass", "Family D", 3, 70),
                    new Creature("Croberry", "Grass", "Family E", 3, 70),
                    new Creature("Velvevoir", "Grass", "Family F", 3, 70),
                    new Creature("Piestoise", "Water", "Family G", 3, 70),
                    new Creature("Icesundae", "Water", "Family H", 3, 70),
                    new Creature("Samurcone", "Water", "Family I", 3, 70),
                    new Creature("Ultimate Nicdao", "Professor", "Family W", 3, 5000)
            );
    
            return getRandomCreature(possibleCreaturesEL3);
        }
    
        protected Creature getRandomCreature(List<Creature> creatures) {
            int randomIndex = random.nextInt(creatures.size());
            return creatures.get(randomIndex);
        }
}
