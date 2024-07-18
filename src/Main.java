import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Author: Max Shoemaker
 * Course: CEN 3024C
 * Date: 6/12/2024
 * Class Name: Main
 * This class contains the main method and serves as the main part of the program.
 * The menu has many options to manage characters and can simulate fights between them.
 * The program allows users to add characters from a file, manually add characters, update character details, delete characters,
 * list all characters, and simulate a fight between two characters.
 */
public class Main {

    /**
     * Default constructor for the Main class.
     * This constructor is provided by the compiler and does not perform any specific actions.
     */
    public Main() {
        // Default constructor
    }

    /**
     * The main method where the program starts execution.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the database URL:");
        String url = scanner.nextLine();

        System.out.println("Enter the database user:");
        String user = scanner.nextLine();

        System.out.println("Enter the database password:");
        String password = scanner.nextLine();

        CharacterManager characterManager = new CharacterManager(url, user, password);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter the file path:");
                        String filePath = scanner.nextLine();
                        addCharactersFromFile(characterManager, filePath);
                        break;

                    case 2:
                        System.out.println("Enter character name:");
                        String name = scanner.nextLine();
                        int power;
                        try {
                            System.out.println("Enter character power level:");
                            power = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number for power level.");
                            break;
                        }
                        System.out.println("Enter character show:");
                        String show = scanner.nextLine();
                        System.out.println("Is the character alive? (true/false):");
                        boolean alive;
                        try {
                            alive = Boolean.parseBoolean(scanner.nextLine());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid input. Please enter true or false.");
                            break;
                        }
                        Character character = new Character(name, power, show, alive);
                        characterManager.addCharacter(character);
                        System.out.println("Character added successfully: " + character);
                        break;

                    case 3:
                        characterManager.listCharacters();
                        System.out.println("Enter the number or name of the character to update:");
                        String updateIdentifier = scanner.nextLine();
                        Character characterToUpdate = findCharacter(characterManager, updateIdentifier);

                        if (characterToUpdate != null) {
                            System.out.println("Enter new name:");
                            String newName = scanner.nextLine();
                            int newPower;
                            try {
                                System.out.println("Enter new power level:");
                                newPower = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number for power level.");
                                break;
                            }
                            System.out.println("Enter new show:");
                            String newShow = scanner.nextLine();
                            System.out.println("Is the character alive? (true/false):");
                            boolean newAlive;
                            try {
                                newAlive = Boolean.parseBoolean(scanner.nextLine());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid input. Please enter true or false.");
                                break;
                            }
                            characterManager.updateCharacter(characterToUpdate.getName(), newName, newPower, newShow, newAlive);
                            System.out.println("Character updated successfully.");
                        } else {
                            System.out.println("Character not found.");
                        }
                        break;

                    case 4:
                        characterManager.listCharacters();
                        System.out.println("Enter the number or name of the character to delete:");
                        String deleteIdentifier = scanner.nextLine();
                        if (!deleteCharacter(characterManager, deleteIdentifier)) {
                            System.out.println("Character not found.");
                        }
                        break;

                    case 5:
                        listCharacterNamesWithNumbers(characterManager);
                        break;

                    case 6:
                        characterManager.listCharacters();
                        break;

                    case 7:
                        fightCharacters(characterManager, scanner);
                        break;

                    case 8:
                        if (confirmAction("Are you sure you want to clear the database? (yes/no): ", scanner)) {
                            clearDatabase(characterManager);
                        }
                        break;

                    case 9:
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid input. Please choose again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter true or false.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Displays the menu options.
     */
    private static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add character from file");
        System.out.println("2. Add a character");
        System.out.println("3. Update a character");
        System.out.println("4. Remove a character");
        System.out.println("5. List characters");
        System.out.println("6. View details");
        System.out.println("7. Fight characters");
        System.out.println("8. Clear database");
        System.out.println("9. Exit");
        System.out.print("Enter: ");
    }

    /**
     * Adds characters from a file.
     * @param characterManager The CharacterManager instance to manage characters.
     * @param filePath The path of the file containing character data.
     */
    static void addCharactersFromFile(CharacterManager characterManager, String filePath) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0].trim();
                    int power = Integer.parseInt(parts[1].trim());
                    String show = parts[2].trim();
                    boolean alive = Boolean.parseBoolean(parts[3].trim());
                    Character character = new Character(name, power, show, alive);
                    characterManager.addCharacter(character);
                    System.out.println("Added character from file: " + character);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            fileScanner.close();
            System.out.println("Characters added from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error checking file.");
        }
    }

    /**
     * Confirms an action with user input.
     * @param message The confirmation message.
     * @param scanner The Scanner instance to read user input.
     * @return True if user confirms (yes), false otherwise (no).
     */
    static boolean confirmAction(String message, Scanner scanner) {
        System.out.print(message);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    /**
     * Clears the database.
     * @param characterManager The CharacterManager instance to clear characters.
     */
    static void clearDatabase(CharacterManager characterManager) {
        characterManager.clearDatabase();
    }

    /**
     * Lists character names with their indices.
     * @param characterManager The CharacterManager instance to retrieve characters.
     */
    static void listCharacterNamesWithNumbers(CharacterManager characterManager) {
        List<Character> characters = characterManager.getCharacters();
        if (characters.isEmpty()) {
            System.out.println("No characters in the list.");
        } else {
            System.out.println("List of Characters:");
            int index = 1;
            for (Character character : characters) {
                System.out.println(index + ". " + character.getName());
                index++;
            }
        }
    }

    /**
     * Finds a character by its name or number.
     * @param characterManager The CharacterManager instance to retrieve characters.
     * @param identifier The name or number of the character.
     * @return The Character if found, null otherwise.
     */
    static Character findCharacter(CharacterManager characterManager, String identifier) {
        Character characterToUpdate = null;

        if (identifier.matches("\\d+")) {
            int updateNumber = Integer.parseInt(identifier);
            List<Character> characters = characterManager.getCharacters();
            if (updateNumber > 0 && updateNumber <= characters.size()) {
                characterToUpdate = characters.get(updateNumber - 1);
            }
        } else {
            characterToUpdate = characterManager.getCharacter(identifier);
        }

        return characterToUpdate;
    }

    /**
     * Deletes a character by its name or index.
     * @param characterManager The CharacterManager instance to delete characters.
     * @param identifier The name or number of the character to delete.
     * @return True if character was found and deleted, false otherwise.
     */
    static boolean deleteCharacter(CharacterManager characterManager, String identifier) {
        if (identifier.matches("\\d+")) {
            int deleteNumber = Integer.parseInt(identifier);
            List<Character> characters = characterManager.getCharacters();
            if (deleteNumber > 0 && deleteNumber <= characters.size()) {
                characterManager.deleteCharacter(deleteNumber - 1);
                return true;
            }
        } else {
            Character characterToDelete = characterManager.getCharacter(identifier);
            if (characterToDelete != null) {
                characterManager.deleteCharacter(identifier);
                return true;
            }
        }
        return false;
    }

    /**
     * Handles fighting between characters.
     * @param characterManager The CharacterManager instance used to retrieve characters from.
     * @param scanner The Scanner instance to read user input.
     */
    static void fightCharacters(CharacterManager characterManager, Scanner scanner) {
        characterManager.listCharacters();
        System.out.println("Enter the number or name of the first character:");
        String firstIdentifier = scanner.nextLine();
        System.out.println("Enter the number or name of the second character:");
        String secondIdentifier = scanner.nextLine();

        Character character1 = findCharacter(characterManager, firstIdentifier);
        Character character2 = findCharacter(characterManager, secondIdentifier);

        if (character1 != null && character2 != null) {
            Fight fight = new Fight();
            String result = fight.battle(character1, character2);
            System.out.println("Fight result: " + result);
        } else {
            System.out.println("One or both characters not found.");
        }
    }
}
