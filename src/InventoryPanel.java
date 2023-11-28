import javax.swing.*;

public class InventoryPanel extends JFrame 
{
    public InventoryPanel(Inventory inventory) 
    {
        setTitle("Inventory");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));

        JLabel inventoryLabel = new JLabel("Inventory:");
        inventoryPanel.add(inventoryLabel);

        for (Creature creature : inventory.getAllCreatures()) 
        {
            JLabel creatureLabel = new JLabel(
            "<html>Name: " + creature.getName() +
            "<br>Type: " + creature.getType() +
            "<br>Family: " + creature.getFamily() +
            "<br>EL: " + creature.getEvolutionLevel() + "</html>"
        );
            inventoryPanel.add(creatureLabel);
        }
        Creature activeCreature = inventory.getActiveCreature();

        // Display active creature information
        JLabel activeCreatureLabel = new JLabel("Active Creature: " + activeCreature.getName());
        inventoryPanel.add(activeCreatureLabel);

        // Button to change the active creature
        JButton changeActiveButton = new JButton("Change Active Creature");
        changeActiveButton.addActionListener(e -> showCreatureSelectionDialog(inventory));
        inventoryPanel.add(changeActiveButton);

        add(inventoryPanel);
        setVisible(true);
    }
            private void showCreatureSelectionDialog(Inventory inventory) 
            {
            Object[] options = inventory.getAllCreatures().toArray();
            Creature selectedCreature = (Creature) JOptionPane.showInputDialog(
                    this,
                    "Select a creature:",
                    "Change Active Creature",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    inventory.getActiveCreature()
            );

            if(selectedCreature != null) 
                {
                inventory.setActiveCreature(selectedCreature); // Set the selected creature as the active creature
                // We need to update the active creature label or refresh the inventory panel
                }
            }
}
