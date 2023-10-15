import java.util.*;

/**
 *  The Area class handles area exploration done by the user
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.3
 */
public class Area
{
   private int[][] tiles; // 2D array of area's tiles
   private int currentX, currentY; // Current X and Y position 
   private int i, j; // Indexes of tiles
   private Random random;


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
            
    
// =================================================================
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
            Random random = new Random();
            int chance = random.nextInt(100); // Generate a random number between 0 and 99

            return chance < 40 ; // 40% chance to encounter a creature
        }

        /**
         * Determines the creature to be encountered in the area.
         *
         * @return The encountered creature.
         */
        public Creature determineEncounteredCreature()
        {
                List<Creature> possibleCreatures = new ArrayList<>();
            possibleCreatures.add(new Creature("Strawander", "Fire", "Family A", 1, 50));
            possibleCreatures.add(new Creature("Chocowool", "Fire", "Family B", 1, 50));
            possibleCreatures.add(new Creature("Parfwit", "Fire", "Family C", 1, 50));
            possibleCreatures.add(new Creature("Brownisaur", "Grass", "Family D", 1, 50));
            possibleCreatures.add(new Creature("Frubat", "Grass", "Family E", 1, 50));
            possibleCreatures.add(new Creature("Malts", "Grass", "Family F", 1, 50));
            possibleCreatures.add(new Creature("Squirpie", "Water", "Family G", 1, 50));
            possibleCreatures.add(new Creature("Chocolite", "Water", "Family H", 1, 50));
            possibleCreatures.add(new Creature("Oshacone", "Water", "Family I", 1, 50));
            possibleCreatures.add(new Creature("Nicdao", "Professor", "Family W", 1, 50));
                int randomIndex = random.nextInt(possibleCreatures.size());

        return possibleCreatures.get(randomIndex);
        }
}