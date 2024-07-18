import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest2 {

    private DatabaseManager dbManager;

    @BeforeEach
    public void setUp() {
        // Create a Scanner to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the database URL
        System.out.print("Enter database URL: ");
        String url = scanner.nextLine();

        // Prompt the user for the database username
        System.out.print("Enter database user: ");
        String user = scanner.nextLine();

        // Prompt the user for the database password
        System.out.print("Enter database password: ");
        String password = scanner.nextLine();

        // Initialize the DatabaseManager with the user-provided credentials
        dbManager = new DatabaseManager(url, user, password);
    }

    @AfterEach
    public void tearDown() {
        dbManager.clearDatabase();
    }

    @Test
    public void testDeleteCharacterFromDatabaseByName() {
        String characterNameToDelete = "CharacterToDelete";
        Character character = new Character(characterNameToDelete, 150, "TestShow", true);
        dbManager.addCharacter(character);
        System.out.println(character + " was successfully added to the database.");

        dbManager.deleteCharacter(characterNameToDelete);

        Character deletedCharacter = dbManager.getCharacter(characterNameToDelete);
        assertNull(deletedCharacter, "Character was not deleted from the database.");
        System.out.println(characterNameToDelete + " was successfully deleted from the database.");
    }

    @Test
    public void testDeleteCharacterFromDatabaseByShow() {
        String characterShowToDelete = "ShowToDelete";
        Character character = new Character("TestCharacter", 150, characterShowToDelete, true);
        dbManager.addCharacter(character);
        System.out.println(character + " was successfully added to the database.");

        dbManager.deleteCharacterByShow(characterShowToDelete);

        List<Character> characters = dbManager.getCharacters();
        boolean found = characters.stream()
                .anyMatch(c -> c.getShow().equals(characterShowToDelete));
        assertFalse(found, "Character was not deleted from the database.");
        System.out.println(characterShowToDelete + " was successfully deleted from the database.");
    }
}
