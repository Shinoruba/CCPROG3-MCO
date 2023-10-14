/**
 *  @code InputHandler class handles inputs, jokes aside- this is a helper class that can have 
 *  multiple classes use the same method for user inputs
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.1
 */
import java.util.Scanner;

        public class InputHandler 
        {
            static Scanner scan = new Scanner(System.in);

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

                } 
                catch(java.util.InputMismatchException e) 
                {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scan.next(); // Eat the invalid input
                }
            }
            return choice;
        }
                  
        public static int getUserSwapChoice(Inventory currentInventory) 
        {
            while(true) 
            {
                System.out.print("Enter the number of the creature you want to swap with: ");
                int choice = scan.nextInt();
                if(choice >= 1 && choice <= currentInventory.getSize()) 
                    return choice;
                else 
                    System.out.println("Invalid choice. Please select a valid creature to swap with.");
                
            }
        }
}

