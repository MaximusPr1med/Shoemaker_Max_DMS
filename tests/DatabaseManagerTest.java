import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {

    @Test
    public void testAddCharacterToDatabase() {
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
        DatabaseManager dbManager = new DatabaseManager(url, user, password);

        // Create a new character to add to the database
        Character character = new Character("TestCharacter", 150, "TestShow", true);
        System.out.println(character + " was added to the database.");

        // Add the character to the database
        dbManager.addCharacter(character);

        // Retrieve all characters from the database
        List<Character> characters = dbManager.getCharacters();

        // Check if the added character is in the list of characters
        boolean found = characters.stream().anyMatch(c -> c.getName().equals(character.getName()));
        assertTrue(found, "Character was not added to the database.");
        System.out.println(character.getName() + " was successfully added to the database.");
    }
}
