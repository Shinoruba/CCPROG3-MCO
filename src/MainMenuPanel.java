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
public class MainMenuPanel extends JPanel 
{

    /**
     * Constructs a MainMenuPanel with buttons for various game actions.
     *
     * @param gameGUI The GameGUI instance to which the buttons are linked.
     */
    public MainMenuPanel(GameGUI gameGUI) 
    {
        setLayout(new GridLayout(4, 1));
        JButton inventoryButton = new JButton("View Inventory"); // Button for viewing the user's inventory
        JButton areaButton = new JButton("Explore Area"); // Button for exploring different game areas
        JButton evolveButton = new JButton("Evolve Creatures"); // Button for evolving creatures
        JButton exitButton = new JButton("Exit"); // Button for exiting the game

        // Action listeners for each button
        inventoryButton.addActionListener(e -> gameGUI.displayInventory());
        areaButton.addActionListener(e -> gameGUI.exploreArea());
        evolveButton.addActionListener(e -> gameGUI.displayEvolutionScreen());
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the panel
        add(inventoryButton);
        add(areaButton);
        add(evolveButton);
        add(exitButton);
    }
}
