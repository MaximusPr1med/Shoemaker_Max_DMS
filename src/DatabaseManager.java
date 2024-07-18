import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Max Shoemaker
 * Course: CEN 3024C
 * Date: 6/17/2024
 * Class Name: DatabaseManager
 * This class contains the DatabaseManager which holds all the database values within MySQL.
 * Using the character table and its columns id, name, series, power, and status.
 */
public class DatabaseManager {
    private Connection connection;

    /**
     * Establishes a connection to the MySQL database using user-provided credentials.
     * @param url the database URL
     * @param user the database username
     * @param password the database password
     */
    public DatabaseManager(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a character to the database.
     * @param character the character object to add to the database
     */
    public void addCharacter(Character character) {
        String query = "INSERT INTO characters (name, power, series, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, character.getName());
            stmt.setInt(2, character.getPowerLevel());
            stmt.setString(3, character.getShow());
            stmt.setBoolean(4, character.isAlive());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing character in the database.
     * @param character the updated character
     * @param originalName the original name of the character to update
     */
    public void updateCharacter(Character character, String originalName) {
        String query = "UPDATE characters SET name = ?, power = ?, series = ?, status = ? WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, character.getName());
            stmt.setInt(2, character.getPowerLevel());
            stmt.setString(3, character.getShow());
            stmt.setBoolean(4, character.isAlive());
            stmt.setString(5, originalName);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Character updated successfully.");
            } else {
                System.out.println("Failed to update character. No character was found with name: " + originalName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a character from the database by name.
     * @param name the name of the character to delete
     */
    public void deleteCharacter(String name) {
        String query = "DELETE FROM characters WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes characters from the database by show.
     * @param show the show of the characters to delete
     */
    public void deleteCharacterByShow(String show) {
        String query = "DELETE FROM characters WHERE series = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, show);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted for show: " + show);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all characters from the database.
     * @return a list of all characters in the database
     */
    public List<Character> getCharacters() {
        List<Character> characters = new ArrayList<>();
        String query = "SELECT * FROM characters";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                int power = rs.getInt("power");
                String series = rs.getString("series");
                boolean status = rs.getBoolean("status");
                characters.add(new Character(name, power, series, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    /**
     * Retrieves a character from the database by name.
     * @param name the name of the character to retrieve
     * @return the character if found, null otherwise
     */
    public Character getCharacter(String name) {
        String query = "SELECT * FROM characters WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int power = rs.getInt("power");
                    String series = rs.getString("series");
                    boolean status = rs.getBoolean("status");
                    return new Character(name, power, series, status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Clears the entire database from the characters table and resets auto-increment.
     */
    public void clearDatabase() {
        String deleteQuery = "DELETE FROM characters";
        String resetAutoIncrementQuery = "ALTER TABLE characters AUTO_INCREMENT = 1";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteQuery);
            stmt.executeUpdate(resetAutoIncrementQuery);
            System.out.println("Database cleared successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to interact with the database manager.
     * Prompts the user for database credentials and starts the application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter database URL: ");
        String url = scanner.nextLine();

        System.out.print("Enter database user: ");
        String user = scanner.nextLine();

        System.out.print("Enter database password: ");
        String password = scanner.nextLine();

        DatabaseManager dbManager = new DatabaseManager(url, user, password);
    }
}
