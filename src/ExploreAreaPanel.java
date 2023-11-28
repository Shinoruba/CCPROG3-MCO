import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Panel representing the exploration of different areas in the Budget Pokemon Game.
 * Allows the user to navigate through areas, encounter creatures, and trigger battles.
 * 
 * Model-View-Controller (MVC) pattern: VIEW
 * 
 * @author Shinoruba
 * @author JSTP8330
 * @version 1.5
 */
public class ExploreAreaPanel extends JFrame 
{
    private static final int AREA_1_ROWS = 5;
    private static final int AREA_1_COLS = 1;
    private static final int AREA_2_ROWS = 3;
    private static final int AREA_2_COLS = 3;
    private static final int AREA_3_ROWS = 4;
    private static final int AREA_3_COLS = 4;

    private int currentRow;
    private int currentCol;
    private int currentArea;
    private Creature userCreature, enemyCreature;
    private Inventory currentInventory;
    private Random random = new Random();

    /**
     * Constructs an ExploreAreaPanel for the given user creature and inventory.
     *
     * @param userCreature The user's active creature.
     * @param currentInventory The current inventory of creatures.
     */
    public ExploreAreaPanel(Creature userCreature, Inventory currentInventory) 
    {
        this.userCreature = userCreature;
        this.currentInventory = currentInventory;
        this.currentRow = 0;
        this.currentCol = 0;
        this.random = new Random();

        setTitle("Explore Area");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        createAreaSelectionPanel();
    }

    /**
     * Creates the area selection panel allowing the user to choose the exploration area.
     */
    private void createAreaSelectionPanel() 
    {
        JPanel areaSelectionPanel = new JPanel();

        // Dropdown menu for selecting the area
        String[] areaOptions = {"Area 1", "Area 2", "Area 3"};
        JComboBox<String> areaDropdown = new JComboBox<>(areaOptions);
        JButton selectAreaButton = new JButton("Select Area");

        selectAreaButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
             {
                String selectedArea = (String) areaDropdown.getSelectedItem();
                switch (selectedArea) 
                {
                    case "Area 1":
                        currentArea = 1;
                        break;
                    case "Area 2":
                        currentArea = 2;
                        break;
                    case "Area 3":
                        currentArea = 3;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + selectedArea);
                }

                createExploreAreaPanel(); // Create the exploration panel based on the selected area
                areaSelectionPanel.setVisible(false); // Hide the area selection panel
            }
        });

        areaSelectionPanel.add(new JLabel("Select Area: "));
        areaSelectionPanel.add(areaDropdown);
        areaSelectionPanel.add(selectAreaButton);

        add(areaSelectionPanel, BorderLayout.NORTH);
        areaSelectionPanel.setVisible(true);
        setVisible(true);
    }

    /**
     * Creates the exploration panel based on the selected area.
     */
    private void createExploreAreaPanel() 
    {
        JPanel exploreAreaPanel = new JPanel(new GridLayout(getAreaRows(), getAreaCols()));

        // Buttons for movement
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");

        upButton.addActionListener(new MoveActionListener(Direction.UP));
        downButton.addActionListener(new MoveActionListener(Direction.DOWN));
        leftButton.addActionListener(new MoveActionListener(Direction.LEFT));
        rightButton.addActionListener(new MoveActionListener(Direction.RIGHT));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttonPanel.add(new JLabel());
        buttonPanel.add(upButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(leftButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(rightButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(downButton);
        buttonPanel.add(new JLabel());

        exploreAreaPanel.add(buttonPanel);

        updateAreaScreen(exploreAreaPanel);

        JButton exitButton = new JButton("Exit Area");
        exitButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the ExploreAreaPanel
            }
        });

        add(exploreAreaPanel, BorderLayout.CENTER);
        add(exitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * ActionListener for handling movement actions in the exploration panel.
     */
    private class MoveActionListener implements ActionListener 
    {
        private Direction direction;

        public MoveActionListener(Direction direction) 
        {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            switch(direction) // Update current position based on the selected direction
            {
                case UP:
                    if (currentRow > 0) currentRow--;
                    break;
                case DOWN:
                    if (currentRow < getAreaRows() - 1) currentRow++;
                    break;
                case LEFT:
                    if (currentCol > 0) currentCol--;
                    break;
                case RIGHT:
                    if (currentCol < getAreaCols() - 1) currentCol++;
                    break;
            }           
            if(random.nextDouble() < 0.4) // Check if the user encounters a creature
            {
                Creature enemyCreature = encounteredCreature();
                BattlePhase battlePhase = new BattlePhase(userCreature, enemyCreature, currentInventory);
                battlePhase.startBattle(100);
            }updateAreaScreen((JPanel) getContentPane().getComponent(0)); // Update the area screen
        }
    }

    /**
     * Updates the area screen with buttons representing the current state of the area.
     *
     * @param exploreAreaPanel The panel containing the area buttons.
     */
    private void updateAreaScreen(JPanel exploreAreaPanel) 
    {
        exploreAreaPanel.removeAll(); // Clear previous components

        for (int row = 0; row < getAreaRows(); row++) 
        {
            for (int col = 0; col < getAreaCols(); col++) 
            {
                JButton tileButton = new JButton("(" + row + ", " + col + ")");
                exploreAreaPanel.add(tileButton);

                
                if(row == currentRow && col == currentCol)// Add a marker for the current position
                {
                    tileButton.setText("X");
                    tileButton.setFont(new Font("Arial", Font.BOLD, 12));
                }

                if(random.nextDouble() < 0.4) // Add a creature encounter with a 40% chance
                {
                    tileButton.setForeground(Color.RED);
                    tileButton.addActionListener(new EncounterActionListener());
                }
            }
        }
        validate();
        repaint();
    }

    /**
     * ActionListener for handling creature encounters in the exploration panel.
     */
    private class EncounterActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Creature enemyCreature = encounteredCreature();
            BattlePhase battlePhase = new BattlePhase(userCreature, enemyCreature, currentInventory);
            battlePhase.startBattle(100);
        }
    }

    /**
     * Generates a random creature encountered during exploration.
     *
     * @return The encountered creature.
     */
    private Creature encounteredCreature()
    {
        int el = random.nextInt(3) + 1;

        switch (el) {
            case 1:
                return encounterCreatureEL1();
            case 2:
                return encounterCreatureEL2();
            case 3:
                return encounterCreatureEL3();
            default:
                throw new IllegalStateException("Unexpected value: " + el);
        }
    }

    /**
     * Generates a random EL1 creature encountered during exploration.
     *
     * @return The encountered creature.
     */
    protected Creature encounterCreatureEL1() 
    {
        List<Creature> possibleCreaturesEL1 = Arrays.asList(
                new Creature("Strawander", "Fire", "Family A", 1, 50),
                new Creature("Chocowool", "Fire", "Family B", 1, 50),
                new Creature("Parfwit", "Fire", "Family C", 1, 50),
                new Creature("Brownisaur", "Grass", "Family D", 1, 50),
                new Creature("Frubat", "Grass", "Family E", 1, 50),
                new Creature("Malts", "Grass", "Family F", 1, 50),
                new Creature("Squirpie", "Water", "Family G", 1, 50),
                new Creature("Chocolite", "Water", "Family H", 1, 50),
                new Creature("Oshacone", "Water", "Family I", 1, 50),
                new Creature("Nicdao", "Professor", "Family W", 1, 50)
        );

        return getRandomCreature(possibleCreaturesEL1);
    }

    /**
     * Generates a random EL2 creature encountered during exploration.
     *
     * @return The encountered creature.
     */
    protected Creature encounterCreatureEL2() 
    {
        List<Creature> possibleCreaturesEL2 = Arrays.asList(
                new Creature("Strawleon", "Fire", "Family A", 2, 50),
                new Creature("Chocofluff", "Fire", "Family B", 2, 50),
                new Creature("Parfure", "Fire", "Family C", 2, 50),
                new Creature("Chocosaur", "Grass", "Family D", 2, 50),
                new Creature("Golberry", "Grass", "Family E", 2, 50),
                new Creature("Kirliecake", "Grass", "Family F", 2, 50),
                new Creature("Tartortle", "Water", "Family G", 2, 50),
                new Creature("Chocolish", "Water", "Family H", 2, 50),
                new Creature("Dewice", "Water", "Family I", 2, 50),
                new Creature("Super Nicdao", "Professor", "Family W", 2, 500)
        );

        return getRandomCreature(possibleCreaturesEL2);
    }

    /**
     * Generates a random EL3 creature encountered during exploration.
     *
     * @return The encountered creature.
     */
    protected Creature encounterCreatureEL3() 
    {
        List<Creature> possibleCreaturesEL3 = Arrays.asList(
                new Creature("Strawizard", "Fire", "Family A", 3, 50),
                new Creature("Candaros", "Fire", "Family B", 3, 50),
                new Creature("Parfelure", "Fire", "Family C", 3, 50),
                new Creature("Fudgansaur", "Grass", "Family D", 3, 50),
                new Creature("Croberry", "Grass", "Family E", 3, 50),
                new Creature("Velvevoir", "Grass", "Family F", 3, 50),
                new Creature("Piestoise", "Water", "Family G", 3, 50),
                new Creature("Icesundae", "Water", "Family H", 3, 50),
                new Creature("Samurcone", "Water", "Family I", 3, 50),
                new Creature("Ultimate Nicdao", "Professor", "Family W", 3, 5000)
        );

        return getRandomCreature(possibleCreaturesEL3);
    }

    /**
     * Retrieves a random creature from the given list.
     *
     * @param creatures The list of creatures to choose from.
     * @return The randomly selected creature.
     */
    protected Creature getRandomCreature(List<Creature> creatures) 
    {
        int randomIndex = random.nextInt(creatures.size());
        return creatures.get(randomIndex);
    }

    /**
     * Retrieves the number of rows for the current exploration area.
     *
     * @return The number of rows.
     */
    private int getAreaRows() 
    {
        switch(currentArea) 
        {
            case 1:
                return AREA_1_ROWS;
            case 2:
                return AREA_2_ROWS;
            case 3:
                return AREA_3_ROWS;
            default:
                throw new IllegalStateException("Unexpected value: " + currentArea);
        }
    }

    /**
     * Retrieves the number of columns for the current exploration area.
     *
     * @return The number of columns.
     */
    private int getAreaCols() 
    {
        switch(currentArea) 
        {
            case 1:
                return AREA_1_COLS;
            case 2:
                return AREA_2_COLS;
            case 3:
                return AREA_3_COLS;
            default:
                throw new IllegalStateException("Unexpected value: " + currentArea);
        }
    }

    /**
     * Enumeration representing the directions for movement.
     */
    private enum Direction 
    {
        UP, 
        DOWN, 
        LEFT, 
        RIGHT
    }
}
