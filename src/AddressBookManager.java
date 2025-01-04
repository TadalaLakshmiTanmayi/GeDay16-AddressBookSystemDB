import java.sql.*;
import java.util.Scanner;

public class AddressBookManager {

    // Method to add a new contact
    public void addContact(Statement statement, Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        System.out.print("Enter state: ");
        String state = scanner.nextLine();
        System.out.print("Enter zip code: ");
        String zip = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        String insertSQL = "INSERT INTO contacts (first_name, last_name, address, city, state, zip, phone_number, email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = statement.getConnection().prepareStatement(insertSQL)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setString(6, zip);
            preparedStatement.setString(7, phoneNumber);
            preparedStatement.setString(8, email);
            preparedStatement.executeUpdate();
            System.out.println("Contact added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding contact: " + e.getMessage());
        }
    }

    // Method to view all contacts
    public void viewContacts(Statement statement) {
        String selectSQL = "SELECT * FROM contacts;";
        try (ResultSet resultSet = statement.executeQuery(selectSQL)) {
            System.out.println("\nContacts:");
            while (resultSet.next()) {
                System.out.printf("ID: %d, First Name: %s, Last Name: %s, Address: %s, City: %s, State: %s, Zip: %s, Phone: %s, Email: %s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("zip"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching contacts: " + e.getMessage());
        }
    }
}
