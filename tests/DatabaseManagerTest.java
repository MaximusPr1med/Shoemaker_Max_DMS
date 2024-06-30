import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {

    @Test
    public void testAddCharacterToDatabase() {

        DatabaseManager dbManager = new DatabaseManager();
        Character character = new Character("TestCharacter", 150, "TestShow", true);
        System.out.println(character + " was added to the database.");

        dbManager.addCharacter(character);

        List<Character> characters = dbManager.getCharacters();
        boolean found = characters.stream().anyMatch(c -> c.getName().equals(character.getName()));
        assertTrue(found, "Character was not added to the database.");
        System.out.println(character.getName() + " was successfully to the database.");
    }
}
