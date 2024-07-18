/**
 * Author: Max Shoemaker
 * Course: CEN 3024C
 * Date: 6/17/2024
 * Class Name: Character
 * This class holds the attributes for the character's name, power level, show, and alive status.
 * It is the main class to get and set attributes for all the characters in the program.
 */
public class Character {
    private String name;
    private int powerLevel;
    private String show;
    private boolean alive;
    private String role;

    /**
     * Constructor name: Character
     * Initializes a new Character object with the following attributes.
     * @param name the name of the character
     * @param powerLevel the power level of the character
     * @param show the show the character is from
     * @param alive the status of the character
     */
    public Character(String name, int powerLevel, String show, boolean alive) {
        this.name = name;
        this.powerLevel = powerLevel;
        this.show = show;
        this.alive = alive;
    }

    /**
     * Method name: getName
     * Returns the name of the character.
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Method name: setName
     * Sets the name of the character.
     * @param name the new name of the character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method name: getPowerLevel
     * Returns the power level of the character.
     * @return the power level of the character
     */
    public int getPowerLevel() {
        return powerLevel;
    }

    /**
     * Method name: setPowerLevel
     * Sets the power level of the character.
     * @param powerLevel the new power level of the character
     */
    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    /**
     * Method name: getShow
     * Returns the show from where the character is from.
     * @return the show the character is from
     */
    public String getShow() {
        return show;
    }

    /**
     * Method name: setShow
     * Sets the show from where the character is from.
     * @param show the new show of the character
     */
    public void setShow(String show) {
        this.show = show;
    }

    /**
     * Method name: isAlive
     * Returns the alive status of the character.
     * @return the alive status of the character
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Method name: setAlive
     * Sets the alive status of the character.
     * @param alive the new alive status of the character
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Method name: toString
     * Returns a string representation of the character, including its name, power level, show, and alive status.
     * @return a string representation of the character
     */
    @Override
    public String toString() {
        return "Character{" +
                "Name='" + name + '\'' +
                ", Power Level=" + powerLevel +
                ", Show='" + show + '\'' +
                ", Alive=" + (alive ? "Yes" : "No") +
                '}';
    }
}
