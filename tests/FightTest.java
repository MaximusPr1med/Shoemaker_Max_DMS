import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FightTest {

    private DatabaseManager dbManager;

    @BeforeEach
    public void setUp() {
        dbManager = new DatabaseManager();
    }

    @AfterEach
    public void tearDown() {
        dbManager.clearDatabase();
    }

    @Test
    public void testBattle_Character1Wins() {
        Character character1 = new Character("Character1", 100, "Show1", true);
        Character character2 = new Character("Character2", 50, "Show2", true);
        dbManager.addCharacter(character1);
        dbManager.addCharacter(character2);
        Fight fight = new Fight();

        String result = fight.battle(character1, character2);

        System.out.println(result);

        assertEquals("Character1 wins!", result);
    }

    @Test
    public void testBattle_Character2Wins() {
        Character character1 = new Character("Character1", 50, "Show1", true);
        Character character2 = new Character("Character2", 100, "Show2", true);
        dbManager.addCharacter(character1);
        dbManager.addCharacter(character2);
        Fight fight = new Fight();

        String result = fight.battle(character1, character2);

        System.out.println(result);

        assertEquals("Character2 wins!", result);
    }

    @Test
    public void testBattle_Tie() {
        Character character1 = new Character("Character1", 75, "Show1", true);
        Character character2 = new Character("Character2", 75, "Show2", true);
        dbManager.addCharacter(character1);
        dbManager.addCharacter(character2);
        Fight fight = new Fight();

        String result = fight.battle(character1, character2);

        System.out.println(result);

        assertEquals("It's a tie!", result);
    }
}
