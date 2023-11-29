import javax.swing.*;
import java.awt.*;

/**
 * Panel representing the main menu of the Budget Pokemon Game.
 * It provides buttons for viewing inventory, exploring the game area,
 * evolving creatures, and exiting the game.
 * 
 * Model-View-Controller (MVC) pattern: VIEW
 * 
 * @author Shinoruba
 * @author JSTP8330
 * @version 1.1
 */
public class MainMenuPanel extends JPanel {

    private GameGUI gameGUI;

     /**
     * Constructs a MainMenuPanel with buttons for various game actions.
     *
     * @param gameGUI The GameGUI instance to which the buttons are linked.
     */
    
    public MainMenuPanel(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));
        initComponents();
    }
    
     /**
     * This method is called from within the constructor to initialize the form.
     */

    private void initComponents() {
        // Initialize and configure components
        JButton inventoryButton = createInventoryButton();
        JButton exploreButton = createExploreButton();
        JButton evolutionButton = createEvolutionButton();
        JButton exitButton = createExitButton();

        // Add components to the panel
        add(inventoryButton);
        add(exploreButton);
        add(evolutionButton);
        add(exitButton);
        
        setVisible(true);
    }

     /**
     * This method creates the button necessary for accessing the entirety 
     * of the creature inventory.
     *
     * @return inventoryButton 
     */
    
    private JButton createInventoryButton() {
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> gameGUI.displayInventory());
        return inventoryButton;
    }
    
     /**
     * This method creates the button necessary for exploration.
     *
     * @return exploreButton 
     */
    
    private JButton createExploreButton() {
        JButton exploreButton = new JButton("Explore");
        exploreButton.addActionListener(e -> gameGUI.exploreArea());
        return exploreButton;
    }
    
     /**
     * This method creates the button necessary for the creatures' evolution.
     *
     * @return exploreButton 
     */

    private JButton createEvolutionButton() {
        JButton evolutionButton = new JButton("Evolution");
        evolutionButton.addActionListener(e -> gameGUI.displayEvolutionScreen());
        return evolutionButton;
    }
    
     /**
     * This method creates the button for exiting the program.
     *
     * @return exitButton 
     */

    private JButton createExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        return exitButton;
    }
}

