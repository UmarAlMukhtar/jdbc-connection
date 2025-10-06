import java.sql.*;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;

public class login {
    public static void main(String[] args) {
        // Load environment variables
        Dotenv dotenv = Dotenv.load();
        String DB_URL = dotenv.get("DB_URL");
        String DB_USER = dotenv.get("DB_USER");
        String DB_PASS = dotenv.get("DB_PASS");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Prepare SQL query
            String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password); // using plain text

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful! Welcome, " + rs.getString("full_name"));
            } else {
                System.out.println("Login failed! Invalid username or password.");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
