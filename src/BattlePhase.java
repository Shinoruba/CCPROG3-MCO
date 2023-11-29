import java.util.*;
/**
 *  The BattlePhase class handles the Battle Phase logic ( obviously lol ), this will include actions like
 *  attacking, swapping, catching, and running away like a loser.
 * 
 *  Model-View-Controller (MVC) pattern: VIEW = Manages the view during battles.
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.6
 */
public class BattlePhase 
{
    private Creature userCreature, enemyCreature;
    private Inventory currentInventory;
    private int maxActions = 3; // Max number of actions per battle
    private Random random = new Random();

    // for laughs and giggles, let's try this  
    private static final int MAX_DAMAGE = 10;
    private static final int MIN_DAMAGE = 1;



    /**
     * Constructs a BattlePhase with the given attributes.
     *
     * @param userCreature           Our creature, both while in main menu and exploring
     * @param enemyCreature           Enemy creature to be encountered during battle.
     * @param currentInventory         Heavily used as a placeholder to all our currently captured creatures.
     */
    public BattlePhase(Creature userCreature, Creature enemyCreature, Inventory currentInventory) 
    {
        this.userCreature = userCreature;
        this.enemyCreature = enemyCreature;
        this.currentInventory = currentInventory;
    }

    /**
     * Starts a battle between the user's creature and an enemy creature.
     *
     * @param enemyHealth The initial health of the enemy creature.
     */
    public void startBattle(int enemyHealth) 
    {
        System.out.println("\n================\n");
        System.out.println("Battle Starts!");
        int actionsRemaining = maxActions;
        boolean shouldEndBattle = false; // Flag to indicate if the battle should end
        while(actionsRemaining > 0 && !shouldEndBattle) 
        {
            displayBattleStatus();// Display available actions
            System.out.println("---");
            System.out.println("Actions remaining: " + actionsRemaining);
            System.out.println("Choose an action:");
            System.out.println("1: Attack");
            System.out.println("2: Swap Creature");
            System.out.println("3: Try to Capture");
            System.out.println("4: Run Away");
            System.out.println("---");

            // Allow the user to choose an action
            InputHandler handler = new InputHandler();
            int userChoice = handler.getUserChoice(1,4);
            System.out.println("\n");


            switch(userChoice) 
            {
                case 1: // user ATTACK RAHHH
                    int userDamage = calculateUserDamage(userCreature, enemyCreature);
                    enemyCreature.setHealth(enemyCreature.getHealth() - userDamage);
                    System.out.println(userCreature.getName() + " attacks " + enemyCreature.getName() + " for " + userDamage + " damage.");
                    actionsRemaining--;
                    if(enemyCreature.getHealth() > 0) 
                    {
                        int enemyDamage = calculateEnemyDamage();
                        userCreature.setHealth(userCreature.getHealth() - enemyDamage);
                        System.out.println(enemyCreature.getName() + " attacks " + userCreature.getName() + " for " + enemyDamage + " damage.");
                    }
                    
                break;
                case 2:// User chose to swap
                    if(currentInventory.getSize() > 1) 
                    {
                        displayInventory(); // Display the user's inventory
                        int swapChoice = InputHandler.getUserSwapChoice(currentInventory);

                        if (swapChoice >= 1 && swapChoice <= currentInventory.getSize()) 
                        {
                            Creature newActiveCreature = currentInventory.getAllCreatures().get(swapChoice - 1);
                            currentInventory.setActiveCreature(newActiveCreature);
                            userCreature = newActiveCreature; // Update in the BattlePhase
                            System.out.println(userCreature.getName() + " is swapped with " + newActiveCreature.getName() + ".");
                        } 
                        else 
                        {
                            System.out.println("Invalid creature selection.");
                        }
                    } 
                else 
                {
                     System.out.println("You don't have any other creatures to swap with.");
                }
                break;
                case 3: // User try to capture
                    if(tryCaptureCreature(enemyCreature)) 
                    {
                        System.out.println("You've successfully captured " + enemyCreature.getName() + "!");
                        System.out.println("=============\n");

                        currentInventory.addCreature(enemyCreature); // Add the captured creature to the user's inventory
                        shouldEndBattle = true;
                    } 
                    else 
                    {
                        System.out.println("Capture attempt failed.");
                    }
                    actionsRemaining--;
                break;
                case 4: // User chose to run away
                    System.out.println("\nYou ran away from the battle!");
                    shouldEndBattle = true; // Set the flag to end the battle
                    System.out.println("\n================");
                    break;
                    default:
                        System.out.println("Invalid choice. Please select a valid action (1-4).");
            }
                if(enemyCreature.getHealth() <= 0) // Check if the enemy is defeated
                {
                    System.out.println(enemyCreature.getName() + " is defeated!");
                    return; // Exit the battle
                }
            // Display enemy's health
            System.out.println(enemyCreature.getName() + " (Health: " + enemyCreature.getHealth() + ")");
       
                if(actionsRemaining == 0 && tryCaptureCreature(enemyCreature)) // Check if user's actions are fully consumed
                {
                    System.out.println("\nThe enemy has run away!");
                    System.out.println("\n================");
                    return; // Exit the battle
                }
        }
    }
// ==================================================================      
/**
 * Sets the current inventory to be used in battle.
 *
 * @param currentInventory The current inventory containing creatures.
 */ 
public void setCurrentInventory(Inventory currentInventory) 
{
    this.currentInventory = currentInventory;
}

    public void displayInventory() 
    {
        List<Creature> allCreatures = currentInventory.getAllCreatures();
        System.out.println("Your inventory:");
        
        for(int i = 0; i < allCreatures.size(); i++) 
        {
            Creature creature = allCreatures.get(i);
            System.out.println((i + 1) + ": " + creature.getName() + " (EL" + creature.getEvolutionLevel() + ")");
        }
    }

    private void displayBattleStatus() 
    {
        System.out.println("\n---");
        System.out.println("Battle Status:");
        System.out.println(userCreature.getName() + " (Health: " + userCreature.getHealth() + ")");
        System.out.println(enemyCreature.getName() + " (Health: " + enemyCreature.getHealth() + ")");
    }

    /**
     * Calculates the damage inflicted by the attacker to the defender.
     * Randomly generates damage between 1 and 10. If there's a type advantage
     * for the attacker over the defender, the damage is increased by 50%.
     *
     * @param attacker The creature that is attacking. ( Player)
     * @param defender The creature that is defending. ( Enemy )
     * @return The calculated damage value.
     */
    public int calculateUserDamage(Creature attacker, Creature defender) 
    {
        int damage = random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
            if(isTypeStrongAgainst(attacker.getType(), defender.getType())) 
            {
                damage *= 1.5;// damage is increased by 50%
            }
        return damage;
    }

    /**
     * Calculates the damage inflicted by the attacker to the defender.
     * Randomly generates damage between 1 and 10.
     * ( This is for the enemy creature )
     *
     * @param attacker The creature that is attacking. ( Enemy )
     * @param defender The creature that is defending. ( Player )
     * @return The calculated damage value.
     */
    public int calculateEnemyDamage()
    {
        return random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
    }


    /**
     * Checks if the attacker's type has an advantage over the defender's type.
     *
     * @param attackerType The type of attacking creature.
     * @param defenderType The type of defending creature.
     * @return True if the attacker has a type advantage.
     * @return False if otherwise.
     */
    private boolean isTypeStrongAgainst(String attackerType, String defenderType) 
    {
        if(attackerType.equals("FIRE") && defenderType.equals("GRASS")) 
        {
            return true;
        } 
        else if(attackerType.equals("GRASS") && defenderType.equals("WATER")) 
        {
            return true;
        } 
        else if(attackerType.equals("WATER") && defenderType.equals("FIRE")) 
        {
            return true;
        }
        return false; // No type advantage
    }

    /**
     * Tries to capture an enemy creature.
     *
     * @param enemyCreature The enemy creature to attempt capturing.
     * @return true if the capture attempt succeeds, false otherwise.
     */
    public boolean tryCaptureCreature(Creature enemyCreature) 
    {
        int catchRate = calculateCatchRate(enemyCreature.getHealth());
        int randomValue = random.nextInt(100); // Generate a random value between 0 and 99

        return randomValue < catchRate;
    }

    /**
     * Calculates the catch rate for capturing an enemy creature.
     *
     * @param enemyHealth The health of the enemy creature.
     * @return The calculated catch rate.
     */
    private int calculateCatchRate(int enemyHealth) 
    {
        return 40 + 50 - enemyHealth;
    }
}
