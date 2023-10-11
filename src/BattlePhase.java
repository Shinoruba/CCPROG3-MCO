/**
 *  @code BattlePhase class handles the Battle Phase logic ( obviously lol ), this will include actions like
 *  attacking, swapping, catching, and running away like a loser
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.0
 */
import java.util.Random;

public class BattlePhase 
{
    private Creature userCreature;
    private Creature enemyCreature;
    private int maxActions = 3; // Max number of actions per battle
    private Random random = new Random();

    public BattlePhase(Creature userCreature, Creature enemyCreature)
    {
        this.userCreature = userCreature;
        this.enemyCreature = enemyCreature;
    }

    public void startBattle() 
    {
        System.out.println("Battle starts!");
        int actionsRemaining = maxActions;

        while (actionsRemaining > 0) 
        {
            displayBattleStatus();

            // User's turn
            int userDamage = calculateUserDamage(userCreature, enemyCreature);
            enemyCreature.setHealth(enemyCreature.getHealth() - userDamage);
            System.out.println(userCreature.getName() + " attacks " + enemyCreature.getName() + " for " + userDamage + " damage.");

            actionsRemaining--;

            // Check if the enemy is defeated
            if (enemyCreature.getHealth() <= 0) 
            {
                System.out.println(enemyCreature.getName() + " is defeated!");
                // Implement the capture mechanism here
                if (tryCaptureCreature(enemyCreature)) {
                    System.out.println("You've successfully captured " + enemyCreature.getName() + "!");
                } else {
                    System.out.println("Capture attempt failed.");
                }
                return; // Exit the battle
            }

            displayBattleStatus();

            // Enemy's turn
            int enemyDamage = calculateUserDamage(enemyCreature, userCreature);
            userCreature.setHealth(userCreature.getHealth() - enemyDamage);
            System.out.println(enemyCreature.getName() + " attacks " + userCreature.getName() + " for " + enemyDamage + " damage.");

            actionsRemaining--;

            // Check if the user's creature is defeated
            if (userCreature.getHealth() <= 0) 
            {
                System.out.println(userCreature.getName() + " is defeated!");
                return; // Exit the battle
            }
        }

        System.out.println("The enemy has run away!");
    }

    private void displayBattleStatus() 
    {
        System.out.println("Battle Status:");
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

    private boolean tryCaptureCreature(Creature enemyCreature) 
    {
        int catchRate = calculateCatchRate(enemyCreature.getHealth());
        int randomValue = random.nextInt(100); // Generate a random value between 0 and 99

        return randomValue < catchRate;
    }

    private int calculateCatchRate(int enemyHealth) 
    {
        return 40 + 50 - enemyHealth;
    }
}