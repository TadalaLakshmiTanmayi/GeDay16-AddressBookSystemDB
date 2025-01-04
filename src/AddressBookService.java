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
                    + "name VARCHAR(100) NOT NULL,"
                    + "email VARCHAR(100),"
                    + "phone VARCHAR(15));";
            statement.execute(createTableSQL);

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. View Contacts");
                System.out.println("2. Add New Contact");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        manager.viewContacts(statement);
                        break;
                    case 2:
                        manager.addContact(statement, scanner);
                        break;
                    case 3:
                        System.out.println("Thank you");
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
