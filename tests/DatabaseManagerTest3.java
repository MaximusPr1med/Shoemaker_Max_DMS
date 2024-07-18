import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest3 {

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
    public void testUpdateCharacterInDatabase() {
        String originalName = "CharacterToUpdate";
        Character character = new Character(originalName, 150, "TestShow", true);
        dbManager.addCharacter(character);

        System.out.println("Character was added into Database: " + character);

        Character updatedCharacter = new Character(originalName, 200, "UpdatedShow", false);
        dbManager.updateCharacter(updatedCharacter, originalName);

        Character retrievedCharacter = dbManager.getCharacter(originalName);
        assertNotNull(retrievedCharacter, "Character was not found in the Database.");
        assertEquals(updatedCharacter.getPowerLevel(), retrievedCharacter.getPowerLevel(),
                "Power level was not updated.");
        assertEquals(updatedCharacter.getShow(), retrievedCharacter.getShow(),
                "Show was not updated.");
        assertEquals(updatedCharacter.isAlive(), retrievedCharacter.isAlive(),
                "Alive status was not updated.");

        System.out.println("Updated Character in Database: " + retrievedCharacter);
    }
}
