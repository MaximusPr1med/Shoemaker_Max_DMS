import java.util.List;
/**
 * Author: Max Shoemaker
 * Course: CEN 3024C
 * Date: 6/17/2024
 * Class Name: CharacterManager
 * This class has all the character attributes that will then be used in DatabaseManager this is the main program code.
 */
public class CharacterManager {
    private DatabaseManager dbManager;

    /**
     * initialize the CharacterManager with a DatabaseManager instance.
     */
    public CharacterManager() {
        dbManager = new DatabaseManager();
    }
    /**
     * adds a new character to the database.
     * @param character is the new character to be added
     */
    public void addCharacter(Character character) {
        dbManager.addCharacter(character);
        System.out.println("Added character: " + character.getName());
    }
    /**
     * updates an existing character in the database.
     * @param oldName       current name of the character to update
     * @param newName       new name for the character
     * @param newPowerLevel new power level for the character
     * @param newShow       new show for the character
     * @param newAlive      new status for the character
     */
    public void updateCharacter(String oldName, String newName, int newPowerLevel, String newShow, boolean newAlive) {
        Character updatedCharacter = new Character(newName, newPowerLevel, newShow, newAlive);
        dbManager.updateCharacter(updatedCharacter, oldName);
        System.out.println("Updated character: " + newName);
    }
    /**
     * deletes a character from the database by name.
     * @param name is the name of the character to delete
     */
    public void deleteCharacter(String name) {
        Character characterToDelete = dbManager.getCharacter(name);
        if (characterToDelete != null) {
            dbManager.deleteCharacter(name);
            System.out.println(name + " has been deleted.");
        } else {
            System.out.println("Invalid character. Please type again.");
        }
    }
    /**
     * deletes a character from the database by the number in the list of characters.
     * @param index the number of the character to delete
     */
    public void deleteCharacter(int index) {
        List<Character> characters = dbManager.getCharacters();
        if (index >= 0 && index < characters.size()) {
            Character characterToRemove = characters.get(index);
            deleteCharacter(characterToRemove.getName());
        } else {
            System.out.println("Invalid character number.");
        }
    }
    /**
     * grabs a character from the database by name.
     * @param name is the name of the character to retrieve from the database.
     * @return the Character if found, null otherwise
     */
    public Character getCharacter(String name) {
        return dbManager.getCharacter(name);
    }
    /**
     * grabs a list of all characters from the database.
     * @return lists all the characters in the database.
     */
    public List<Character> getCharacters() {
        return dbManager.getCharacters();
    }
    /**
     * lists all characters currently stored in the database.
     * print their name, power level, show, and alive status.
     */
    public void listCharacters() {
        List<Character> characters = dbManager.getCharacters();
        if (characters.isEmpty()) {
            System.out.println("No characters in the list.");
        } else {
            System.out.println("List of Characters:");
            int index = 1;
            for (Character character : characters) {
                System.out.println(index + ". " + character.getName() + ", " + character.getPowerLevel() + ", " + character.getShow() + ", " + (character.isAlive() ? "Alive" : "Dead"));
                index++;
            }
        }
    }
    /**
     * wipe the database
     */
    public void clearDatabase() {
        dbManager.clearDatabase();
    }
}
