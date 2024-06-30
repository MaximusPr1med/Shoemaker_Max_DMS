/**
 * Author: Max Shoemaker
 * Course: CEN 3024C
 * Date: 6/17/2024
 * Class Name: Character
 * this class holds the attributes for the characters name, power, show, and alive status.
 * it is the main get and set attributes for all the characters in the program.
 */
public class Character {
    private String name;
    private int powerLevel;
    private String show;
    private boolean alive;
    private String role;

    /**
     * constructor name: Character
     * initializes a new Character object with the following attributes.
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
    public String getName() {
        return name;
    }
    /**
     * method name: setName
     * sets the name of the character.
     * @param name the new name of the character
     * @return void
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * method name: getPowerLevel
     * returns the power level of the character.
     * @return int the power level of the character
     */

    public int getPowerLevel() {
        return powerLevel;
    }

    /**
     * method name: setPowerLevel
     * sets the power level of the character.
     * @param powerLevel the new power level of the character
     * @return void
     */

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    /**
     * method name: getShow
     * returns the show from where the character is from.
     * @return string the show the character is from
     */

    public String getShow() {
        return show;
    }

    /**
     * method name: setShow
     * sets the show from where the character is from.
     * @param show the new show of the character
     * @return void
     */
    public void setShow(String show) {
        this.show = show;
    }

    /**
     * method name: isAlive
     * returns the alive status of the character.
     * @return boolean the alive status of the character
     */

    public boolean isAlive() {
        return alive;
    }

    /**
     * method name: setAlive
     * sets the alive status of the character.
     * @param alive the new alive status of the character
     * @return void
     */

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * method name: toString
     * returns a string of the character, including its name, power level, show, and alive status.
     * @return string a string representation of the character
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
