import javax.swing.*;

/**
 * The main class representing the graphical user interface for the Budget Pokemon Game.
 * It extends JFrame to create the game's window.
 * 
 * Model-View-Controller (MVC) pattern: CONTROLLER
 * 
 * 
 * @author Shinoruba
 * @author JSTP8330
 * @version 1.2
 */
public class GameGUI extends JFrame 
{
    private Inventory inventory;
    private Creature userCreature;
    private Area currentArea;
    private ExploreAreaPanel exploreAreaPanel;

     /**
     * Constructs a GameGUI with the given inventory.
     *
     * @param inventory The inventory to be used in the gui game file, which is this one.
     */

    public GameGUI(Inventory inventory) {
        this.inventory = inventory;
        this.currentArea = new Area(5, 5);

        selectStarterCreature();

        setTitle("Budget Pokemon Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMainMenu();
    }


    /**
     * Selects a starter creature for the user from three available options.
     * Displays a dialog box for the user to choose an EL1 creature.
     */
    private void selectStarterCreature() 
    {
        // 3 EL1 starter creatures, one from each element!
        Creature starterCreature1 = new Creature("Strawander", "Fire", "A", 1, 100);
        Creature starterCreature2 = new Creature("Squirpie", "Water", "G", 1, 100);
        Creature starterCreature3 = new Creature("Brownisaur", "Grass", "D", 1, 100);

        // Display a list of EL1 creatures for the user to choose from

        Object[] options = {starterCreature1.getName(), starterCreature2.getName(), starterCreature3.getName()};
        int starterChoice = JOptionPane.showOptionDialog(
                this,
                "Select a Starter Creature (EL1):",
                "Starter Creature Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        Creature starterCreature = null;
        switch (starterChoice) 
        {
            case 0:
                starterCreature = starterCreature1;
                break;
            case 1:
                starterCreature = starterCreature2;
                break;
            case 2:
                starterCreature = starterCreature3;
                break;
            default:
                // Exit program when user closes the dialog box
                System.exit(0);
        }

        // Add the starter creature to the inventory and set it as active
        inventory.addCreature(starterCreature);
        inventory.setActiveCreature(starterCreature);
        userCreature = starterCreature;

        JOptionPane.showMessageDialog(
                this,
                "You've selected " + starterCreature.getName() + " as your starter creature!",
                "Starter Creature Selection",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Creates the main menu panel and adds it to the game window.
     */
    private void createMainMenu()
    {
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        add(mainMenuPanel);
    }

    /**
     * Displays the user's inventory using the InventoryPanel.
     */
    public void displayInventory() 
    {
        InventoryPanel inventoryPanel = new InventoryPanel(inventory);
    }

    /**
     * Initializes the ExploreAreaPanel for exploring 3 different game areas.
     */
    public void exploreArea() 
    {
        exploreAreaPanel = new ExploreAreaPanel(userCreature, inventory);
    }

    /**
     * Displays the evolution screen using the EvolutionScreenPanel.
     */
    public void displayEvolutionScreen() 
    {
        EvolutionScreenPanel evolutionScreenPanel = new EvolutionScreenPanel(inventory);
    }

    /**
     * The main method to launch the game.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> {
            Inventory inventory = new Inventory();
            GameGUI gameGUI = new GameGUI(inventory);
            gameGUI.setVisible(true);
        });
    }
}
