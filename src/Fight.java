/**
 * Author: Max Shoemaker
 * Course: CEN 3024C
 * Date: 6/17/2024
 * Class Name: Fight
 * This class provides a method to simulate a battle between two characters.
 * It compares their power levels and determines the winner or if it's a tie.
 */
public class Fight {

    /**
     * Default constructor for the Fight class.
     * Initializes a new instance of the Fight class.
     */
    public Fight() {
        // default constructor
    }

    /**
     * Simulates a battle between two characters based on their power levels.
     * Comparing the power levels of the two characters and returns a string indicating the winner or if it's a tie.
     * @param character1 the first character participating in the battle
     * @param character2 the second character participating in the battle
     * @return String the result of the battle, indicating which character wins or if it's a tie
     */
    public String battle(Character character1, Character character2) {
        if (character1.getPowerLevel() > character2.getPowerLevel()) {
            return character1.getName() + " wins!";
        } else if (character1.getPowerLevel() < character2.getPowerLevel()) {
            return character2.getName() + " wins!";
        } else {
            return "It's a tie!";
        }
    }
}
