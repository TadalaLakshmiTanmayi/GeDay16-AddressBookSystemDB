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

    // Method to edit an existing contact
    public void editContact(Statement statement, Scanner scanner) {
        System.out.print("Enter the first name of the contact to edit: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter the last name of the contact to edit: ");
        String lastName = scanner.nextLine();

        // Check if the contact exists
        String selectSQL = "SELECT * FROM contacts WHERE first_name = ? AND last_name = ?";
        try (PreparedStatement preparedStatement = statement.getConnection().prepareStatement(selectSQL)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // If the contact exists, display current details
                System.out.println("Found contact:");
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

                // Ask the user what they want to update
                System.out.println("Which detail would you like to update?");
                System.out.println("1. Address");
                System.out.println("2. City");
                System.out.println("3. State");
                System.out.println("4. Zip Code");
                System.out.println("5. Phone Number");
                System.out.println("6. Email");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                String updateSQL = null;
                switch (choice) {
                    case 1:
                        System.out.print("Enter new address: ");
                        String address = scanner.nextLine();
                        updateSQL = "UPDATE contacts SET address = ? WHERE first_name = ? AND last_name = ?";
                        try (PreparedStatement updateStatement = statement.getConnection().prepareStatement(updateSQL)) {
                            updateStatement.setString(1, address);
                            updateStatement.setString(2, firstName);
                            updateStatement.setString(3, lastName);
                            updateStatement.executeUpdate();
                            System.out.println("Address updated successfully!");
                        }
                        break;
                    case 2:
                        System.out.print("Enter new city: ");
                        String city = scanner.nextLine();
                        updateSQL = "UPDATE contacts SET city = ? WHERE first_name = ? AND last_name = ?";
                        try (PreparedStatement updateStatement = statement.getConnection().prepareStatement(updateSQL)) {
                            updateStatement.setString(1, city);
                            updateStatement.setString(2, firstName);
                            updateStatement.setString(3, lastName);
                            updateStatement.executeUpdate();
                            System.out.println("City updated successfully!");
                        }
                        break;
                    case 3:
                        System.out.print("Enter new state: ");
                        String state = scanner.nextLine();
                        updateSQL = "UPDATE contacts SET state = ? WHERE first_name = ? AND last_name = ?";
                        try (PreparedStatement updateStatement = statement.getConnection().prepareStatement(updateSQL)) {
                            updateStatement.setString(1, state);
                            updateStatement.setString(2, firstName);
                            updateStatement.setString(3, lastName);
                            updateStatement.executeUpdate();
                            System.out.println("State updated successfully!");
                        }
                        break;
                    case 4:
                        System.out.print("Enter new zip code: ");
                        String zip = scanner.nextLine();
                        updateSQL = "UPDATE contacts SET zip = ? WHERE first_name = ? AND last_name = ?";
                        try (PreparedStatement updateStatement = statement.getConnection().prepareStatement(updateSQL)) {
                            updateStatement.setString(1, zip);
                            updateStatement.setString(2, firstName);
                            updateStatement.setString(3, lastName);
                            updateStatement.executeUpdate();
                            System.out.println("Zip code updated successfully!");
                        }
                        break;
                    case 5:
                        System.out.print("Enter new phone number: ");
                        String phoneNumber = scanner.nextLine();
                        updateSQL = "UPDATE contacts SET phone_number = ? WHERE first_name = ? AND last_name = ?";
                        try (PreparedStatement updateStatement = statement.getConnection().prepareStatement(updateSQL)) {
                            updateStatement.setString(1, phoneNumber);
                            updateStatement.setString(2, firstName);
                            updateStatement.setString(3, lastName);
                            updateStatement.executeUpdate();
                            System.out.println("Phone number updated successfully!");
                        }
                        break;
                    case 6:
                        System.out.print("Enter new email: ");
                        String email = scanner.nextLine();
                        updateSQL = "UPDATE contacts SET email = ? WHERE first_name = ? AND last_name = ?";
                        try (PreparedStatement updateStatement = statement.getConnection().prepareStatement(updateSQL)) {
                            updateStatement.setString(1, email);
                            updateStatement.setString(2, firstName);
                            updateStatement.setString(3, lastName);
                            updateStatement.executeUpdate();
                            System.out.println("Email updated successfully!");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Contact not found!");
            }
        } catch (SQLException e) {
            System.err.println("Error updating contact: " + e.getMessage());
        }
    }
    // Method to delete a contact
    public void deleteContact(Statement statement, Scanner scanner) {
        System.out.print("Enter the first name of the contact to delete: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter the last name of the contact to delete: ");
        String lastName = scanner.nextLine();

        String deleteSQL = "DELETE FROM contacts WHERE first_name = ? AND last_name = ?";
        try (PreparedStatement preparedStatement = statement.getConnection().prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("Contact not found!");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting contact: " + e.getMessage());
        }
    }
    // Method to retrieve contacts based on city or state
    public void retrieveContactsByCityOrState(Statement statement, Scanner scanner) {
        System.out.println("Search by:");
        System.out.println("1. City");
        System.out.println("2. State");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String searchSQL = null;
        String searchValue = null;

        switch (choice) {
            case 1:
                System.out.print("Enter the city: ");
                searchValue = scanner.nextLine();
                searchSQL = "SELECT * FROM contacts WHERE city = ?";
                break;
            case 2:
                System.out.print("Enter the state: ");
                searchValue = scanner.nextLine();
                searchSQL = "SELECT * FROM contacts WHERE state = ?";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        try (PreparedStatement preparedStatement = statement.getConnection().prepareStatement(searchSQL)) {
            preparedStatement.setString(1, searchValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean found = false;
            System.out.println("Contacts found:");
            while (resultSet.next()) {
                found = true;
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

            if (!found) {
                System.out.println("No contacts found for the given city or state.");
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving contacts: " + e.getMessage());
        }
    }
    // Method to get the count of contacts based on city or state
    public void getContactCountByCityOrState(Statement statement, Scanner scanner) {
        System.out.println("Get count of contacts by:");
        System.out.println("1. City");
        System.out.println("2. State");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String countSQL = null;
        String searchValue = null;

        switch (choice) {
            case 1:
                System.out.print("Enter the city: ");
                searchValue = scanner.nextLine();
                countSQL = "SELECT COUNT(*) FROM contacts WHERE city = ?";
                break;
            case 2:
                System.out.print("Enter the state: ");
                searchValue = scanner.nextLine();
                countSQL = "SELECT COUNT(*) FROM contacts WHERE state = ?";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        try (PreparedStatement preparedStatement = statement.getConnection().prepareStatement(countSQL)) {
            preparedStatement.setString(1, searchValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);  // Get the count from the result set
                System.out.println("Number of contacts in the " + (choice == 1 ? "city" : "state") + " '" + searchValue + "': " + count);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving contact count: " + e.getMessage());
        }
    }
}
