import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Max Shoemaker
 * Course: CEN 3024C
 * Date: 6/30/2024
 * Class Name: MainGUI
 * This class uses buttons and GUI elements for the user to have an easier and better experience when running the code.
 */
public class MainGUI {
    private CharacterManager characterManager;
    private JFrame frame;
    private JTextArea outputArea;

    /**
     * Constructs the MainGUI with database credentials and initializes the GUI components.
     * @param url Database URL.
     * @param user Database username.
     * @param password Database password.
     */
    public MainGUI(String url, String user, String password) {
        // Initialize character manager and set up the GUI frame
        characterManager = new CharacterManager(url, user, password);
        frame = new JFrame("Character Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create output text area and add it to scroll pane
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Create control panel with buttons for various operations
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(10, 1));

        // Initialize buttons and their action listeners
        JButton addFromFileButton = new JButton("Add Characters from File");
        addFromFileButton.addActionListener(e -> addCharactersFromFile());

        JButton addCharacterButton = new JButton("Add Character");
        addCharacterButton.addActionListener(e -> addCharacter());

        JButton updateCharacterButton = new JButton("Update Character");
        updateCharacterButton.addActionListener(e -> updateCharacter());

        JButton deleteCharacterButton = new JButton("Delete Character");
        deleteCharacterButton.addActionListener(e -> deleteCharacter());

        JButton listCharactersButton = new JButton("List Characters");
        listCharactersButton.addActionListener(e -> listCharacters());

        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.addActionListener(e -> viewDetails());

        JButton fightCharactersButton = new JButton("Fight Characters");
        fightCharactersButton.addActionListener(e -> fightCharacters());

        JButton clearDatabaseButton = new JButton("Clear Database");
        clearDatabaseButton.addActionListener(e -> clearDatabase());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to control panel
        controlPanel.add(addFromFileButton);
        controlPanel.add(addCharacterButton);
        controlPanel.add(updateCharacterButton);
        controlPanel.add(deleteCharacterButton);
        controlPanel.add(listCharactersButton);
        controlPanel.add(viewDetailsButton);
        controlPanel.add(fightCharactersButton);
        controlPanel.add(clearDatabaseButton);
        controlPanel.add(exitButton);

        // Add components to frame and make it visible
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    /**
     * Adds characters to the database from a file specified by the user.
     */
    private void addCharactersFromFile() {
        String filePath = JOptionPane.showInputDialog(frame, "Enter the file path:");
        if (filePath != null && !filePath.isEmpty()) {
            Main.addCharactersFromFile(characterManager, filePath);
            outputArea.setText("Characters added from file.\n");
        }
    }

    /**
     * Prompts user to add a character with input fields for name, power level, show, and alive status.
     */
    private void addCharacter() {
        JTextField nameField = new JTextField();
        JTextField powerField = new JTextField();
        JTextField showField = new JTextField();
        JCheckBox aliveCheckBox = new JCheckBox("Alive");

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Power Level:"));
        panel.add(powerField);
        panel.add(new JLabel("Show:"));
        panel.add(showField);
        panel.add(new JLabel("Alive:"));
        panel.add(aliveCheckBox);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Add Character", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int power;
            try {
                power = Integer.parseInt(powerField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number for power level.");
                return;
            }
            String show = showField.getText();
            boolean alive = aliveCheckBox.isSelected();

            Character character = new Character(name, power, show, alive);
            characterManager.addCharacter(character);
            outputArea.append("Character added: " + character + "\n");
        }
    }

    /**
     * Updates a character's details based on user input for name, power level, show, and alive status.
     */
    private void updateCharacter() {
        String identifier = JOptionPane.showInputDialog(frame, "Enter character name or number to update:");
        if (identifier != null && !identifier.isEmpty()) {
            Character character = Main.findCharacter(characterManager, identifier);
            if (character != null) {
                JTextField nameField = new JTextField(character.getName());
                JTextField powerField = new JTextField(String.valueOf(character.getPowerLevel()));
                JTextField showField = new JTextField(character.getShow());
                JCheckBox aliveCheckBox = new JCheckBox("Alive", character.isAlive());

                JPanel panel = new JPanel(new GridLayout(5, 2));
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Power Level:"));
                panel.add(powerField);
                panel.add(new JLabel("Show:"));
                panel.add(showField);
                panel.add(new JLabel("Alive:"));
                panel.add(aliveCheckBox);

                int result = JOptionPane.showConfirmDialog(frame, panel, "Update Character", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String newName = nameField.getText();
                    int newPower = Integer.parseInt(powerField.getText());
                    String newShow = showField.getText();
                    boolean newAlive = aliveCheckBox.isSelected();

                    characterManager.updateCharacter(character.getName(), newName, newPower, newShow, newAlive);
                    outputArea.append("Character updated: " + newName + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Character not found.");
            }
        }
    }

    /**
     * Deletes a character from the database based on user input for name or number.
     */
    private void deleteCharacter() {
        String identifier = JOptionPane.showInputDialog(frame, "Enter character name or number to delete:");
        if (identifier != null && !identifier.isEmpty()) {
            Main.deleteCharacter(characterManager, identifier);
            outputArea.append("Character deleted.\n");
        }
    }

    /**
     * Lists all characters in the database with their details in a formatted manner.
     */
    private void listCharacters() {
        outputArea.setText("Characters:\n");

        // Retrieve the list of characters from characterManager
        List<Character> characters = characterManager.getCharacters();

        // Iterate through characters and format them as required
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Character character : characters) {
            sb.append(index).append(". ")
                    .append(character.getName()).append(", ")
                    .append(character.getPowerLevel()).append(", ")
                    .append(character.getShow()).append(", ")
                    .append(character.isAlive() ? "Alive" : "Dead")
                    .append("\n");
            index++;
        }

        // Set the formatted string to the outputArea
        outputArea.append(sb.toString());
    }

    /**
     * Displays details of a selected character based on user input for name or number.
     */
    private void viewDetails() {
        String identifier = JOptionPane.showInputDialog(frame, "Enter character name or number to view:");
        if (identifier != null && !identifier.isEmpty()) {
            Character character = Main.findCharacter(characterManager, identifier);
            if (character != null) {
                outputArea.setText("Character Details:\n" + character + "\n");
            } else {
                JOptionPane.showMessageDialog(frame, "Character not found.");
            }
        }
    }

    /**
     * Initiates a fight between two selected characters based on user input for their names or numbers.
     */
    private void fightCharacters() {
        String firstIdentifier = JOptionPane.showInputDialog(frame, "Enter the first character name or number:");
        String secondIdentifier = JOptionPane.showInputDialog(frame, "Enter the second character name or number:");
        if (firstIdentifier != null && secondIdentifier != null) {
            Character character1 = Main.findCharacter(characterManager, firstIdentifier);
            Character character2 = Main.findCharacter(characterManager, secondIdentifier);

            if (character1 != null && character2 != null) {
                Fight fight = new Fight();
                String result = fight.battle(character1, character2);
                outputArea.setText("Fight Result: " + result + "\n");
            } else {
                JOptionPane.showMessageDialog(frame, "One or both characters not found.");
            }
        }
    }

    /**
     * Clears the entire character database based on user confirmation.
     */
    private void clearDatabase() {
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear the database?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            characterManager.clearDatabase();
            outputArea.setText("Database cleared.\n");
        }
    }

    /**
     * Main method to start the GUI application.
     * Prompts for database credentials and starts the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String url = JOptionPane.showInputDialog(null, "Enter database URL:");
        if (url == null || url.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Database URL cannot be empty. Exiting...");
            System.exit(1);
        }

        String user = JOptionPane.showInputDialog(null, "Enter database username:");
        if (user == null || user.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Database username cannot be empty. Exiting...");
            System.exit(1);
        }

        String password = JOptionPane.showInputDialog(null, "Enter database password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Database password cannot be empty. Exiting...");
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> new MainGUI(url, user, password));
    }
}
