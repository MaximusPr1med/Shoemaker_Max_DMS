import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest3 {

    @Test
    public void testUpdateCharacterInDatabase() {

        DatabaseManager dbManager = new DatabaseManager();
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

        dbManager.clearDatabase();
    }
}
