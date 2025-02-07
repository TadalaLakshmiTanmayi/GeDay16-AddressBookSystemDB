import java.sql.*;
import java.util.Scanner;

public class AddressBookService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/AddressBookDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "tanu25*T";
    private static AddressBookManager manager = new AddressBookManager();

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the database successfully!");

            // Create table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(100) NOT NULL,"
                    + "last_name VARCHAR(100) NOT NULL,"
                    + "address VARCHAR(255),"
                    + "city VARCHAR(100),"
                    + "state VARCHAR(100),"
                    + "zip VARCHAR(20),"
                    + "phone_number VARCHAR(15),"
                    + "email VARCHAR(100));";
            statement.execute(createTableSQL);

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. View Contacts");
                System.out.println("2. Add New Contact");
                System.out.println("3. Edit Contact");
                System.out.println("4. Delete Contact");
                System.out.println("5. Retrieve Contacts by City or State");
                System.out.println("6. Get Contact Count by City or State");
                System.out.println("7. Retrieve Sorted Contacts by City");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        manager.viewContacts(statement);
                        break;
                    case 2:
                        manager.addContact(statement, scanner);
                        break;
                    case 3:
                        manager.editContact(statement, scanner);
                        break;
                    case 4:
                        manager.deleteContact(statement, scanner);
                        break;
                    case 5:
                        manager.retrieveContactsByCityOrState(statement, scanner);
                        break;
                    case 6:
                        manager.getContactCountByCityOrState(statement, scanner);
                        break;
                    case 7:
                        manager.retrieveSortedContactsByCity(statement, scanner);
                        break;
                    case 8:
                        System.out.println("Thank you!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}