import javax.swing.*;
import java.util.List;

public class EvolutionScreenPanel extends JFrame {

    public EvolutionScreenPanel(Inventory inventory) {
        setTitle("Evolve Creatures");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel evolvePanel = new JPanel();
        evolvePanel.setLayout(new BoxLayout(evolvePanel, BoxLayout.Y_AXIS));

        JLabel evolutionLabel = new JLabel("Evolution Screen:");
        evolvePanel.add(evolutionLabel);

        JTextArea evolutionTextArea = new JTextArea();
        evolutionTextArea.setEditable(false);
        evolutionTextArea.setRows(5);
        evolutionTextArea.setColumns(5);

        List<Creature> creatureList = inventory.getAllCreatures();

        for (int i = 0; i < creatureList.size(); i++) {
            Creature creature = creatureList.get(i);
            evolutionTextArea.append("[" + (i + 1) + "] " + creature.toString() + "\n");
        }

        evolvePanel.add(evolutionTextArea);

        JButton evolveButton = new JButton("Evolve Selected Creatures");
        evolveButton.addActionListener(e -> {
            try {
                int index1 = Integer.parseInt(JOptionPane.showInputDialog("Enter index of first creature:")) - 1;
                int index2 = Integer.parseInt(JOptionPane.showInputDialog("Enter index of second creature:")) - 1;

                inventory.evolveCreatures(index1, index2);
                JOptionPane.showMessageDialog(null, "Evolution successful!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid index.");
            }
            dispose();
        });

        evolvePanel.add(evolveButton);
        add(evolvePanel);
        setVisible(true);
    }
}
