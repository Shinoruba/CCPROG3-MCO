import javax.swing.*;

/**
 * Panel representing the inventory of creatures in the Budget Pokemon Game.
 * It displays information about each creature in the inventory and allows
 * the user to view and change the active creature.
 * 
 * Model-View-Controller (MVC) pattern: VIEW
 * 
 * @author Shinoruba
 * @author JSTP8330
 * @version 1.1
 */
public class InventoryPanel extends JFrame 
{
    /**
     * Constructs an InventoryPanel to display the user's inventory.
     *
     * @param inventory The inventory containing the user's creatures.
     */
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

        // Display information for each creature in the inventory, why for each loop? tried to be cool
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
            /**
             * Displays a dialog for selecting a new active creature from the inventory.
             *
             * @param inventory The inventory containing the user's creatures.
             */
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

            if(selectedCreature != null) // INCOMPLETE BTW
                {
                inventory.setActiveCreature(selectedCreature); // Set the selected creature as the active creature
                // We need to update the active creature label or refresh the inventory panel
                }
            }
}
