/**
 * @code Area class handles area exploration done by the user
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Area
{
   private int[][] tiles; // 2D array of area's tiles
   private int currentX, currentY; // Current X and Y position 
   private int i, j; // Indexes of tiles

    public Area(int nRows, int nCols)
    {
        tiles = new int[nRows][nCols];
        currentX = 0; // starting X position
        currentY = 0; // starting Y position

        for(i=0; i<nRows; i++)
        {
            for(j=0; j<nCols; j++)
            {
                tiles[i][j]=0;
            }
        }
    }
            public int getCurrentX() 
            {
                return currentX;
            }

            public void setCurrentX(int currentX) 
            {
                this.currentX = currentX;
            }
        
            public int getCurrentY() 
            {
                return currentY;
            }
            
            public void setCurrentY(int currentY) 
            {
                this.currentY = currentY;
            }

            public int[][] getTiles() 
            {
                return tiles;
            }

        
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


        
            
        public boolean shouldEncounterCreature() // Used in exploreArea() method
        {
            Random random = new Random();
            int chance = random.nextInt(100); // Generate a random number between 0 and 99

            return chance < 40; // 40% chance to encounter a creature
        }

        public Creature determineEncounteredCreature()
        {
            List<Creature> possibleCreatures = new ArrayList<>();

            possibleCreatures.add(new Creature("Strawander", "Fire", "Family A", 1, 50));
            possibleCreatures.add(new Creature("Chocowool", "Fire", "Family B", 1, 100));
            possibleCreatures.add(new Creature("Parfwit", "Fire", "Family C", 1, 100));
            possibleCreatures.add(new Creature("Brownisaur", "Grass", "Family D", 1, 100));
            possibleCreatures.add(new Creature("Frubat", "Grass", "Family E", 1, 100));
            possibleCreatures.add(new Creature("Malts", "Grass", "Family F", 1, 100));
            possibleCreatures.add(new Creature("Squirpie", "Water", "Family G", 1, 100));
            possibleCreatures.add(new Creature("Chocolite", "Water", "Family H", 1, 100));
            possibleCreatures.add(new Creature("Oshacone", "Water", "Family I", 1, 100));
            possibleCreatures.add(new Creature("Cubillas", "skrr", "Family L", 1, 100));

            Random random = new Random();
            int randomIndex = random.nextInt(possibleCreatures.size());

            return possibleCreatures.get(randomIndex);
        }
}