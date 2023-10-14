/**
 *  @code BattlePhase class handles the Battle Phase logic ( obviously lol ), this will include actions like
 *  attacking, swapping, catching, and running away like a loser
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.2
 */
import java.util.ArrayList;
import java.util.Random;
// import java.util.Scanner;

public class BattlePhase 
{
    private Creature userCreature;
    private Creature enemyCreature;
    private Inventory currentInventory;
    private int maxActions = 3; // Max number of actions per battle
    private Random random = new Random();

    public BattlePhase(Creature userCreature, Creature enemyCreature, Inventory currentInventory) {
        this.userCreature = userCreature;
        this.enemyCreature = enemyCreature;
        this.currentInventory = currentInventory; // Initialize the currentInventory here.
    }

    // public void setCurrentInventory(Inventory currentInventory) {
    //     this.currentInventory = currentInventory;
    // }

    public void startBattle(int enemyHealth) 
    {
        System.out.println("=============================");
        System.out.println("Battle starts!");
        int actionsRemaining = maxActions;
        while (actionsRemaining > 0) 
        {
            displayBattleStatus();// Display available actions
            System.out.println("---");
            System.out.println("Actions remaining: " + actionsRemaining);
            System.out.println("Choose an action:");
            System.out.println("1: Attack");
            System.out.println("2: Swap Creature");
            System.out.println("3: Try to Capture");
            System.out.println("4: Run Away");

            // Allow the user to choose an action
            InputHandler handler = new InputHandler();
            int userChoice = handler.getUserChoice(1,4);

            switch (userChoice) {
                case 1:
                int userDamage = calculateUserDamage(userCreature, enemyCreature);
                enemyCreature.setHealth(enemyCreature.getHealth() - userDamage);
                System.out.println(userCreature.getName() + " attacks " + enemyCreature.getName() + " for " + userDamage + " damage.");
                actionsRemaining--;
                if(enemyCreature.getHealth() > 0) 
                {
                    int enemyDamage = calculateEnemyDamage(enemyCreature, userCreature);
                    userCreature.setHealth(userCreature.getHealth() - enemyDamage);
                    System.out.println(enemyCreature.getName() + " attacks " + userCreature.getName() + " for " + enemyDamage + " damage.");
                }
                break;
                case 2:
                    // User chose to swap
                    if (currentInventory.getSize() > 1) {
                        displayInventory(); // Display the user's inventory
                        int swapChoice = InputHandler.getUserSwapChoice(currentInventory);

                        if (swapChoice >= 1 && swapChoice <= currentInventory.getSize()) {
                            Creature newActiveCreature = currentInventory.getAllCreatures().get(swapChoice - 1);
                            currentInventory.setActiveCreature(newActiveCreature);
                            System.out.println(userCreature.getName() + " is swapped with " + newActiveCreature.getName() + ".");
                        } else {
                            System.out.println("Invalid creature selection.");
                        }
                    } else {
                        System.out.println("You don't have any other creatures to swap with.");
                    }
                break;
                case 3:
                    // User chose to catch
                    if (tryCaptureCreature(enemyCreature)) {
                        System.out.println("You've successfully captured " + enemyCreature.getName() + "!");
                        currentInventory.addCreature(enemyCreature); // Add the captured creature to the user's inventory
                    } else {
                        System.out.println("Capture attempt failed.");
                    }
                    actionsRemaining--;
                break;
                case 4:
                    // User chose to run away
                    System.out.println("You ran away from the battle!");
                    return; // Exit the battle
                default:
                    System.out.println("Invalid choice. Please select a valid action (1-4).");
            }


            // Check if the enemy is defeated
        if (enemyCreature.getHealth() <= 0) {
            System.out.println(enemyCreature.getName() + " is defeated!");
            return; // Exit the battle
        }

        // Display enemy's health
        System.out.println(enemyCreature.getName() + " (Health: " + enemyCreature.getHealth() + ")");

        // Check if user's actions are fully consumed
        if (actionsRemaining == 0) {
            System.out.println("The enemy has run away!");
            return; // Exit the battle
        }
        }
    }


private void displayInventory() {
    ArrayList<Creature> allCreatures = currentInventory.getAllCreatures();
    System.out.println("Your inventory:");
    
    for (int i = 0; i < allCreatures.size(); i++) {
        Creature creature = allCreatures.get(i);
        System.out.println((i + 1) + ": " + creature.getName() + " (EL" + creature.getEvolutionLevel() + ")");
    }
}

    private void displayBattleStatus() 
    {
        System.out.println("\nBattle Status:");
        System.out.println(userCreature.getName() + " (Health: " + userCreature.getHealth() + ")");
        System.out.println(enemyCreature.getName() + " (Health: " + enemyCreature.getHealth() + ")");
    }

    private int calculateUserDamage(Creature attacker, Creature defender) 
    {
        Random random = new Random();
        int damage = random.nextInt(10) + 1; // Random damage between 1 and 10

        // Check type advantage
        if (isTypeStrongAgainst(attacker.getType(), defender.getType())) 
        {
            damage *= 1.5; // Type advantage: damage is increased by 50%
        }

        return damage;
    }

    private int calculateEnemyDamage(Creature attacker, Creature defender) 
    {
        Random random = new Random();
        int damage = random.nextInt(10) + 1; // Random damage between 1 and 10
        return damage;
    }

    private boolean isTypeStrongAgainst(String attackerType, String defenderType) {
        if (attackerType.equals("FIRE") && defenderType.equals("GRASS")) {
            return true;
        } else if (attackerType.equals("GRASS") && defenderType.equals("WATER")) {
            return true;
        } else if (attackerType.equals("WATER") && defenderType.equals("FIRE")) {
            return true;
        }
        return false; // No type advantage
    }

    public boolean tryCaptureCreature(Creature enemyCreature) 
    {
        int catchRate = calculateCatchRate(enemyCreature.getHealth());
        int randomValue = random.nextInt(100); // Generate a random value between 0 and 99

        return randomValue < catchRate;
    }

    private int calculateCatchRate(int enemyHealth) 
    {
        return 40 + 50 - enemyHealth;
    }

    public void setCurrentInventory(Inventory currentInventory2) {
    }
}