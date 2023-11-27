import javax.swing.*;

public class InventoryPanel extends JFrame {

    public InventoryPanel(Inventory inventory) {
        setTitle("Inventory");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));

        JLabel inventoryLabel = new JLabel("Inventory:");
        inventoryPanel.add(inventoryLabel);

        for (Creature creature : inventory.getAllCreatures()) {
            JLabel creatureLabel = new JLabel(creature.getName() + " (EL" + creature.getEvolutionLevel() + ")");
            inventoryPanel.add(creatureLabel);
        }

        add(inventoryPanel);
        setVisible(true);
    }
}
