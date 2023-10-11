/**
 * @code Area class handles area exploration done by the user
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.0
 */

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
}