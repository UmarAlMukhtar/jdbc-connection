import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class jdbc {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String DB_URL = dotenv.get("DB_URL");
        String USER = dotenv.get("USER");
        String PASS = dotenv.get("PASS");
        String QUERY = "SELECT * FROM books";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next()) {
                System.out.println(rs.getInt("book_id") + " - " + rs.getString("title"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
