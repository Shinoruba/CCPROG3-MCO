import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * The `MainGame` class controls the flow of the game, including the menu, user inputs, and transitions between exploration and battle.
 * 
 * @author Shinoruba
 * @author JSTP8330
 * @version 2.3
 */
public class MainGame 
{
    private Inventory currentInventory;
    private Area currentArea;
    private Creature encounteredEnemy;

    private Scanner scan;
    InputHandler handler = new InputHandler();

    /**
     * Constructs a MainGame object.
     */
    public MainGame() 
    {
        currentInventory = new Inventory();
        setCurrentArea(1);
        scan = new Scanner(System.in);
    }

    /**
     * Gets the current user's inventory.
     * @return The current user's inventory.
     */
    public Inventory getCurrentInventory() 
    {
        return currentInventory;
    }

/**
 * Sets the current area based on the specified area level.
 *
 * @param areaLevel The level or ID of the area.
 */
private void setCurrentArea(int areaLevel) 
{
    switch (areaLevel) 
    {
        case 1:
            currentArea = new Area1();
            break;
        case 2:
            currentArea = new Area2();
            break;
        case 3:
            currentArea = new Area3();
            break;
        default:
            throw new IllegalArgumentException("Invalid area level: " + areaLevel);
    }
    currentArea.setCurrentX(0);
    currentArea.setCurrentY(0);
}

 /**
     * Starts the game.
     */
    public void startGame()
    {
        System.out.println("================\n");
        System.out.println("Welcome to the Budget Pokemon game!\n");
        selectStarterCreature();

        Creature userCreature = currentInventory.getActiveCreature();
        BattlePhase battlePhase = new BattlePhase(userCreature, encounteredEnemy, currentInventory);
        battlePhase.setCurrentInventory(currentInventory);

        while(true)
        {
            displayMainMenu();
            int choice = getUserMenuChoice();
            handleUserChoice(choice);
        }
    }

    /**
     * Selects a starter creature for the player, there will be 3 choices!
     */
    private void selectStarterCreature() // Add a method to select the starter creature
    {
        // 3 El1 starter creatures, one from each element!
        Creature starterCreature1 = new Creature("Strawander", "Fire", "A", 1, 100);
        Creature starterCreature2 = new Creature("Squirpie", "Water", "G", 1, 100);
        Creature starterCreature3 = new Creature("Brownisaur", "Grass", "D", 1, 100);
        // Display a list of EL1 creatures for the user to choose from
        ArrayList<Creature> el1Creatures = new ArrayList<>();
        el1Creatures.add(starterCreature1);
        el1Creatures.add(starterCreature2);
        el1Creatures.add(starterCreature3);

        System.out.println("Select a starter creature (EL1):");
        for(int i = 0; i < el1Creatures.size(); i++)
        {
            System.out.println((i + 1) + ": " + el1Creatures.get(i).getName());
        }
        int starterChoice = handler.getUserChoice(1, el1Creatures.size());
        Creature starterCreature = el1Creatures.get(starterChoice - 1);

        // Add the starter creature to the inventory and set it as active
        currentInventory.addCreature(starterCreature);
        currentInventory.setActiveCreature(starterCreature);

        System.out.println("\nYou've selected " + starterCreature.getName() + " as your starter creature!\n");
        System.out.println("================");
    }

    private void displayMainMenu() // Used in startGame() method
    {
        System.out.println("\nMain Menu:");
        System.out.println("1: View Inventory");
        System.out.println("2: Explore Area");
        System.out.println("3: Evolve Creature");
        System.out.println("4: Exit Game");
    }

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            /**
             * Displays the player's inventory, allowing them to view and manage their creatures.
             */
            private void viewInventory() // 1. View Inventory
            {
                List<Creature> allCreatures = currentInventory.getAllCreatures();
                Creature activeCreature = currentInventory.getActiveCreature();// Get current active creature
                int i;
                System.out.println("\nInventory:");
                    if(activeCreature != null) 
                    {
                        System.out.println("Active Creature:");
                        System.out.println(activeCreature.toString());
                    } 
                    else 
                    {
                        System.out.println("No active creature.");
                    }

                    if(allCreatures.isEmpty()) 
                    {
                        System.out.println("Your inventory is empty.");
                        System.out.println("================");
                    } 
                    else 
                    {
                        System.out.println("Captured Creatures:");
                        for(i=0;i < allCreatures.size();i++) 
                        {
                            Creature creature = allCreatures.get(i);
                            System.out.println((i + 1) + ": " + creature.getName() + " (EL" + creature.getEvolutionLevel() + ")");
                            System.out.println("\n");
                        }
                    }

                if (allCreatures.size()>1) // Check if there are multiple creatures in the inventory
                {
                    System.out.print("Do you want to change your active creature? (y/n): ");
                    String choice = scan.next().toLowerCase();
                
                    while(!choice.equals("y") && !choice.equals("n")) 
                    {
                        System.out.print("Invalid input. Please enter 'y' or 'n': ");
                        choice = scan.next().toLowerCase();
                    }
                
                    if(choice.equals("y")) 
                    {
                        System.out.print("Enter the number of the creature you want to set as active: ");
                        int creatureNumber;
                
                        while(true) 
                        {
                            try
                            {
                                if(scan.hasNextInt()) 
                                {
                                    creatureNumber = scan.nextInt();
                                    if(creatureNumber >= 1 && creatureNumber <= allCreatures.size()) 
                                    {
                                        break; // Valid input, exit the loop
                                    }
                                    else 
                                    {
                                        System.out.print("Invalid creature number. Enter a valid number: ");
                                    }
                                } 
                                else 
                                {
                                    System.out.print("Invalid input. Please enter a valid number: ");
                                    scan.next(); // Consume the invalid input
                                }
                            }catch(java.util.InputMismatchException e) 
                            {
                                System.out.print("Invalid input. Please enter a valid number: ");
                                scan.next(); // Consume invalid input
                            }
                        }
                        currentInventory.setActiveCreature(allCreatures.get(creatureNumber - 1));
                        System.out.println("Active creature set to: " + allCreatures.get(creatureNumber - 1).getName());
                    }
                }
            }

            /**
             * Allows the player to explore the current area, potentially encountering creatures ( there is an easter egg creature ).
             */
            private void exploreArea() // 2. Explore Area
            {
                System.out.println("Exploring the area...\n");
        
                // Display the available areas for exploration
                System.out.println("Select an area to explore:");
                System.out.println("1: Area 1 (5x1)");
                System.out.println("2: Area 2 (3x3)");
                System.out.println("3: Area 3 (4x4)");
        
                // Get the user's choice for the area
                int areaChoice = getUserAreaChoice();
        
                // Set the current area based on the user's choice
                switch (areaChoice) {
                    case 1:
                        currentArea = new Area1();
                        break;
                    case 2:
                        currentArea = new Area2();
                        break;
                    case 3:
                        currentArea = new Area3();
                        break;
                }
        
                // Display the current area screen with the player's position marked.
                currentArea.displayArea();
        
                // Allow the player to move within the area.
                Direction moveDirection = getPlayerMoveDirection();
                encounteredEnemy = null;
        
                if (encounteredEnemy == null) {
                    encounteredEnemy = currentArea.encounteredCreature();
                    encounteredEnemy.getHealth();
                }
        
                System.out.println("You've encountered a creature!");
        
                Creature userCreature = currentInventory.getActiveCreature();
                BattlePhase battle = new BattlePhase(userCreature, encounteredEnemy, currentInventory);
                battle.startBattle(encounteredEnemy.getHealth()); // Use enemy's actual health
        
                if (userCreature.getHealth() <= 0) {
                    System.out.println("Your creature was defeated!");
                    currentInventory.removeCreature(userCreature);
                    System.out.println("You've been returned to a safe location.");
                } else if (encounteredEnemy.getHealth() <= 0) {
                    System.out.println("You defeated the enemy creature!");
                    if (battle.tryCaptureCreature(encounteredEnemy)) {
                        System.out.println("You've successfully captured " + encounteredEnemy.getName() + "!");
                        currentInventory.addCreature(encounteredEnemy);
                    } else
                        System.out.println("Capture attempt failed.");
                }
        
                // Update the player's position based on the move direction.
                updatePlayerPosition(moveDirection);
            }

            /**
             * Handles the evolution of a creature. Not yet implemented.
             */
            private void evolveCreature() // 3. Evolve the Creature
            {
                viewInventory();

                if (currentInventory.getSize() < 2) {
                    System.out.println("You need at least two creatures to evolve. Capture more creatures!");
                    return;
                }
            
                System.out.print("Enter the number of the first creature for evolution: ");
                int index1 = handler.getUserChoice(1, currentInventory.getSize()) - 1;
            
                System.out.print("Enter the number of the second creature for evolution: ");
                int index2 = handler.getUserChoice(1, currentInventory.getSize()) - 1;
            
                // Display evolution screen
                currentInventory.displayEvolutionScreen(index1, index2);  
            }

            /**
             * Exits the game.
             */
            private void exitGame() // 4. Exit the game bye bye aaauughhh please help me
            {
                System.out.println("Exiting the game. Give us a decent grade for effort :( thank  you!");
                scan.close();
                System.exit(0); // method terminates JVM bye bye bye bye bye bye bye bye help me
            }

    
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~       
    /**
     * Handles the player's move direction (UP, DOWN, LEFT, or RIGHT) in the current area.
     */
    public enum Direction
    {
            /**
             * Represents the UP direction.
             */
            UP,
            
            /**
             * Represents the DOWN direction.
             */
            DOWN,
            
            /**
             * Represents the LEFT direction.
             */
            LEFT,
            
            /**
             * Represents the RIGHT direction.
             */
            RIGHT
    }

    private Direction getPlayerMoveDirection()
    {
        while(true)
        {
            System.out.print("Enter your move (UP/DOWN/LEFT/RIGHT): ");
            String input = scan.next().toUpperCase();

            int currentX = currentArea.getCurrentX();
            int currentY = currentArea.getCurrentY();

            if(input.equals("UP"))
            {
                if(currentX > 0)
                {
                    return Direction.UP;
                }
                else
                {
                    System.out.println("You cannot move UP from here.");
                }
            }
            else if(input.equals("DOWN"))
            {
                if(currentX < currentArea.getTiles().length - 1)
                {
                    return Direction.DOWN;
                }
                else
                {
                    System.out.println("You cannot move DOWN from here.");
                }
            }
            else if(input.equals("LEFT"))
            {
                if(currentY > 0)
                {
                    return Direction.LEFT;
                }
                else
                {
                    System.out.println("You cannot move LEFT from here.");
                }
            }
            else if(input.equals("RIGHT"))
            {
                if(currentY < currentArea.getTiles()[currentX].length - 1)
                {
                    return Direction.RIGHT;
                }
                else
                {
                    System.out.println("You cannot move RIGHT from here.");
                }
            } else
                System.out.println("Invalid direction. Please enter UP, DOWN, LEFT, or RIGHT.");
        }
    }

    /**
     * Updates the player's position based on the chosen move direction.
     *
     * @param moveDirection The direction in which the player wants to move.
     */
    private void updatePlayerPosition(Direction moveDirection) // Used in exploreArea() method
    {
        int currentX = currentArea.getCurrentX();
        int currentY = currentArea.getCurrentY();

        switch(moveDirection)
        {
            case UP:
                if(currentX > 0)
                {
                    currentArea.setCurrentX(currentX - 1);
                }
                break;

            case DOWN:
                if(currentX < currentArea.getTiles().length - 1)
                {
                    currentArea.setCurrentX(currentX + 1);
                }
                break;

            case LEFT:
                if(currentY > 0)
                {
                    currentArea.setCurrentY(currentY - 1);
                }
                break;

            case RIGHT:
                if(currentY < currentArea.getTiles()[currentX].length - 1)
                {
                    currentArea.setCurrentY(currentY + 1);
                }
                break;
        }
    }

    /**
     * Gets the user's menu choice (1-4) and validates it.
     *
     * @return The validated user's choice.
     */
    private int getUserMenuChoice()
    {
        int choice;
        while(true){
            try
            {
                choice = handler.getUserChoice(1, 4);
                return choice;
            }catch(java.util.InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid number (1-4).");
                scan.next(); // Consume the invalid input
            }
        }
    }

    /**
     * Gets the user's choice for the area (1-3) and validates it.
     *
     * @return The validated user's choice.
     */
    private int getUserAreaChoice() {
        int choice;
        while (true) {
            try {
                choice = handler.getUserChoice(1, 3);
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number (1-3).");
                scan.next(); // Consume the invalid input
            }
        }
    }

    /**
     * Handles the user's choice from the main menu and invokes corresponding methods.
     *
     * @param choice The user's selected menu choice.
     */
    private void handleUserChoice(int choice)
    {
        switch(choice)
        {
            case 1:
                viewInventory();
                break;
            case 2:
                exploreArea();
                break;
            case 3:
                evolveCreature();
                break;
            case 4:
                exitGame();
                break;
            default:
                System.out.println("Invalid choice BEEEP!!! Please select a valid option, thank you.");
        }
    }

    /**
     * The main entry point for the game.
     * 
     * This method serves as the starting point for the game. It initializes the game controller, selects the
     * starter creature, and enters a loop to display the main menu and handle user input for game actions.
     * The game continues until the user chooses to exit.
     * 
     * @param args The cmd argument.
     */
    public static void main(String[] args) 
    {
        MainGame gameController = new MainGame();
        gameController.startGame();
    }
}
