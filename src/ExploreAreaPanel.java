import javax.swing.*;

public class ExploreAreaPanel extends JFrame {

    private Area currentArea;

    public ExploreAreaPanel(Area currentArea) {
        this.currentArea = selectArea();
        setTitle("Explore Area");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel explorePanel = new JPanel();
        explorePanel.setLayout(new BoxLayout(explorePanel, BoxLayout.Y_AXIS));

        JLabel areaLabel = new JLabel("Current Area:");
        explorePanel.add(areaLabel);

        JTextArea areaTextArea = new JTextArea();
        areaTextArea.setEditable(false);
        areaTextArea.setRows(5);
        areaTextArea.setColumns(5);
        areaTextArea.setText(this.currentArea.displayAreaToString());

        explorePanel.add(areaTextArea);

        JButton encounterButton = new JButton("Encounter Creature");
        encounterButton.addActionListener(e -> {
            Creature encounteredCreature = this.currentArea.encounteredCreature();
            JOptionPane.showMessageDialog(null, "Encountered Creature: " + encounteredCreature.getName());
        });

        explorePanel.add(encounterButton);

        add(explorePanel);
        setVisible(true);
    }

    private Area selectArea() {
        String[] options = {"Area 1", "Area 2", "Area 3"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Select an area to explore:",
                "Area Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0:
                return new Area1(5, 1);
            case 1:
                return new Area2(3, 3);
            case 2:
                return new Area3(4, 4);
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}