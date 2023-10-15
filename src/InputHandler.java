/**
 *  The InputHandler class handles inputs, jokes aside- this is a helper class that can have 
 *  multiple classes use the same method for user inputs
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.1
 */
import java.util.Scanner;

public class InputHandler 
{
    private static Scanner scan = new Scanner(System.in);

    /**
     * Get the user's choice within the specified range.
     *
     * @param min The minimum valid choice.
     * @param max The maximum valid choice.
     * @return The user's choice within the specified range.
     */
    public int getUserChoice(int min, int max) 
    {
        int choice;
        while(true) 
        {
            try 
            {
                System.out.print("Enter your choice: ");
                choice = scan.nextInt();
                if(choice >= min && choice <= max) 
                    break;
                else 
                    System.out.println("Invalid choice. Please select a valid option.");
            }catch(java.util.InputMismatchException e) 
            {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.next(); // Consume the invalid input
            }
        }
        return choice;
    }

    /**
     * Get the user's choice for swapping creatures in the inventory.
     *
     * @param currentInventory The current user's inventory.
     * @return The user's choice for swapping creatures.
     */
    public static int getUserSwapChoice(Inventory currentInventory) 
    {
        while(true) 
        {
            System.out.print("Enter the number of the creature you want to swap with: ");
            int choice = scan.nextInt();
            if(choice >= 1 && choice <= currentInventory.getSize()) 
            {
                return choice;
            } 
            else 
            {
                System.out.println("Invalid choice. Please select a valid creature to swap with.");
            }
        }
    }

    /**
     * Close the scanner to release resources, look how cute and useful this silly thing is.
     */
    public static void closeScanner() 
    {
        scan.close();
    }
}