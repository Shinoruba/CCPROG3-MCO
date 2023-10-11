/**
 *  @code InputHandler class handles inputs, jokes aside- this is a helper class that can have 
 *  multiple classes use the same method for user inputs
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.0
 */


 
import java.util.Scanner;

public class InputHandler 
{
    static Scanner scan = new Scanner(System.in);
















        public int getUserChoice(int min, int max) 
        {
            int choice;
            do 
            {
                System.out.print("Enter your choice: ");
                choice = scan.nextInt();
            }
            while(choice < min || choice > max);

            return choice;
        }

        



}

