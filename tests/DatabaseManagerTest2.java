import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest2 {

    private DatabaseManager dbManager = new DatabaseManager();

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
        System.out.println(characterNameToDelete + " was successfully deleted from the database.");    }

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
