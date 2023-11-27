import javax.swing.*;

public class GameGUI extends JFrame {

    private Inventory inventory;
    private Area currentArea;
    private ExploreAreaPanel exploreAreaPanel;

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



    private void selectStarterCreature() {
        // 3 EL1 starter creatures, one from each element!
        Creature starterCreature1 = new Creature("Strawander", "Fire", "A", 1, 100);
        Creature starterCreature2 = new Creature("Squirpie", "Water", "G", 1, 100);
        Creature starterCreature3 = new Creature("Brownisaur", "Grass", "D", 1, 100);

        // Display a list of EL1 creatures for the user to choose from
        Object[] options = {starterCreature1.getName(), starterCreature2.getName(), starterCreature3.getName()};
        int starterChoice = JOptionPane.showOptionDialog(
                this,
                "Select a starter creature (EL1):",
                "Starter Creature Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        Creature starterCreature;
        switch (starterChoice) {
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
                throw new IllegalStateException("Unexpected value: " + starterChoice);
        }

        // Add the starter creature to the inventory and set it as active
        inventory.addCreature(starterCreature);
        inventory.setActiveCreature(starterCreature);

        JOptionPane.showMessageDialog(
                this,
                "You've selected " + starterCreature.getName() + " as your starter creature!",
                "Starter Creature Selection",
                JOptionPane.INFORMATION_MESSAGE
        );
    }







    private void createMainMenu() {
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        add(mainMenuPanel);
    }

    public void displayInventory() {
        InventoryPanel inventoryPanel = new InventoryPanel(inventory);
    }

    public void exploreArea() {
        ExploreAreaPanel exploreAreaPanel = new ExploreAreaPanel(currentArea);
    }

    public void displayEvolutionScreen() {
        EvolutionScreenPanel evolutionScreenPanel = new EvolutionScreenPanel(inventory);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inventory inventory = new Inventory();
            GameGUI gameGUI = new GameGUI(inventory);
            gameGUI.setVisible(true);
        });
    }
}
