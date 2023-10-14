/**
 *  @code Game class controls the flow of the game, this includes the menu, user inputs, and transitions between
 *  exploration and battle
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 2.0
 */

import java.util.ArrayList;
// import java.util.Random;
import java.util.Scanner;

public class MainGame
{
    private Inventory currentInventory;
    private Area currentArea;
    private Creature encounteredEnemy;
    private int encounteredEnemyHealth = 50;
    private Scanner scan;
    InputHandler handler = new InputHandler();
    public MainGame() 
    {
        currentInventory = new Inventory();
        currentArea = new Area(5,1);
        scan = new Scanner(System.in);
    }

    public Inventory getCurrentInventory() {
        return currentInventory;
    }

                public void startGame() 
                {
                    System.out.println("Welcome to the budget pokemon game!");
                    selectStarterCreature();

                    Creature userCreature = currentInventory.getActiveCreature();
                    BattlePhase battlePhase = new BattlePhase(userCreature, encounteredEnemy, currentInventory);
                    battlePhase.setCurrentInventory(currentInventory);

                    while (true) {
                        displayMainMenu();
                        
                        int choice = handler.getUserChoice(1,4); // only 1,2,3,4 options are allowed
                        
                        switch (choice) 
                        {
                            case 1: viewInventory();    break;
                            case 2: exploreArea();      break;
                            case 3: evolveCreature();   break;
                            case 4: exitGame();         break;
                            default:
                                System.out.println("Invalid choice BEEEP!!! Please select a valid option, thank you.");
                        }
                    }
                }

                // Add a method to select the starter creature
                private void selectStarterCreature() 
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

                        System.out.println("You've selected " + starterCreature.getName() + " as your starter creature!\n");
                        System.out.println("=======");
                }

    private void displayMainMenu() // Used in startGame() method
    {
        System.out.println("Main Menu:");
        System.out.println("1: View Inventory");
        System.out.println("2: Explore Area");
        System.out.println("3: Evolve Creature");
        System.out.println("4: Exit Game");
    }

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // 1. View Inventory
            private void viewInventory() 
            {
                ArrayList<Creature> allCreatures = currentInventory.getAllCreatures();
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

                // Check if there are multiple creatures in the inventory
                    if(allCreatures.size()>1) 
                    {
                        System.out.print("Do you want to change your active creature? (y/n): ");
                        String choice = scan.next().toLowerCase();

                        if(choice.equals("y")) 
                        {
                            System.out.print("Enter the number of the creature you want to set as active: ");
                            int creatureNumber = scan.nextInt();

                            if(creatureNumber >= 1 && creatureNumber <= allCreatures.size()) 
                            {
                                currentInventory.setActiveCreature(allCreatures.get(creatureNumber - 1));
                                System.out.println("Active creature set to: " + allCreatures.get(creatureNumber - 1).getName());
                            } 
                            else 
                            {
                                System.out.println("Invalid creature number.");
                            }
                        }
                    }
            }

        // 2. Explore Area
            private void exploreArea() 
            {
                System.out.println("Exploring the area...");
                
                // Display the current area screen with the player's position marked.
                currentArea.displayArea();

                // Allow the player to move within the area.
                Direction moveDirection = getPlayerMoveDirection();
                encounteredEnemyHealth = 50;// Initialize the enemy's health
                // Check if the player encounters a creature.
                if (currentArea.shouldEncounterCreature()) {
                    if (encounteredEnemy == null) {
                        encounteredEnemy = currentArea.determineEncounteredCreature();
                         
                    }
        
                    System.out.println("You've encountered a creature!");
                    Creature userCreature = currentInventory.getActiveCreature();
                    BattlePhase battle = new BattlePhase(userCreature, encounteredEnemy, currentInventory);
                    battle.startBattle(encounteredEnemyHealth);
        
                    if (userCreature.getHealth() <= 0) {
                        System.out.println("Your creature was defeated!");
                        currentInventory.removeCreature(userCreature);
                        System.out.println("You've been returned to a safe location.");
                    } else if (encounteredEnemyHealth <= 0) {
                        System.out.println("You defeated the enemy creature!");
        
                        if (battle.tryCaptureCreature(encounteredEnemy)) {
                            System.out.println("You've successfully captured " + encounteredEnemy.getName() + "!");
                            currentInventory.addCreature(encounteredEnemy);
                        } else {
                            System.out.println("Capture attempt failed.");
                        }
                    }
                }
                
                // Update the player's position based on the move direction.
                updatePlayerPosition(moveDirection);
            }

        // 3. Evolve the Creature    
            private void evolveCreature() 
            {
                System.out.println("Not yet implemented! Please wait for future updates and patches.");    
            }

        // 4. Exit the game bye bye aaauughhh please help me
            private void exitGame() 
            {
                System.out.println("Exiting the game. Give us a good grade thank  you!");
                scan.close();
                System.exit(0); // method terminates JVM bye bye bye bye bye bye bye bye help me
            }

    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~       
            // Get the player's move direction (UP, DOWN, LEFT, or RIGHT).
                public enum Direction 
                    {
                        UP,DOWN,LEFT,RIGHT;
                    }
            private Direction getPlayerMoveDirection() // Used in exploreArea() method
            {
                while (true) 
                {
                    System.out.print("Enter your move (UP/DOWN/LEFT/RIGHT): ");
                    String input = scan.next().toUpperCase();

                    try 
                    {
                        Direction direction = Direction.valueOf(input);
                        return direction;
                    } 
                    catch (IllegalArgumentException e) 
                    {
                        System.out.println("Invalid direction. Please enter UP, DOWN, LEFT, or RIGHT.");
                    }
                }
            } 

            // Update the player's position based on the move direction.
            private void updatePlayerPosition(Direction moveDirection) // Used in exploreArea() method
            {
                int currentX = currentArea.getCurrentX();
                int currentY = currentArea.getCurrentY();

                switch (moveDirection) 
                {
                    case UP:
                        if (currentX > 0) 
                        {
                            currentArea.setCurrentX(currentX - 1);
                        }
                        break;
                    case DOWN:
                        if (currentX < currentArea.getTiles().length - 1) 
                        {
                            currentArea.setCurrentX(currentX + 1);
                        }
                        break;
                    case LEFT:
                        if (currentY > 0) 
                        {
                            currentArea.setCurrentY(currentY - 1);
                        }
                        break;
                    case RIGHT:
                        if (currentY < currentArea.getTiles()[currentX].length - 1) 
                        {
                            currentArea.setCurrentY(currentY + 1);
                        }
                        break;
                }
            }
    // wow just look how clean main method looks
    public static void main(String[] args) 
    {
        MainGame gameController = new MainGame();
        gameController.startGame();
    }
}