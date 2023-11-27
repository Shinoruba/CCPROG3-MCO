import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(GameGUI gameGUI) {
        setLayout(new GridLayout(4, 1));

        JButton inventoryButton = new JButton("View Inventory");
        JButton areaButton = new JButton("Explore Area");
        JButton evolveButton = new JButton("Evolve Creatures");
        JButton exitButton = new JButton("Exit");

        inventoryButton.addActionListener(e -> gameGUI.displayInventory());
        areaButton.addActionListener(e -> gameGUI.exploreArea());
        evolveButton.addActionListener(e -> gameGUI.displayEvolutionScreen());
        exitButton.addActionListener(e -> System.exit(0));

        add(inventoryButton);
        add(areaButton);
        add(evolveButton);
        add(exitButton);
    }
}
