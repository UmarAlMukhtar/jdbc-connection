import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private Connection conn;

    public DBHelper() throws SQLException, ClassNotFoundException {
        Dotenv dotenv = Dotenv.load();
        String DB_URL = dotenv.get("DB_URL");
        String DB_USER = dotenv.get("DB_USER");
        String DB_PASS = dotenv.get("DB_PASS");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public boolean checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        boolean success = rs.next();
        rs.close();
        stmt.close();
        return success;
    }

    public List<String> getAllBooks() throws SQLException {
        List<String> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            books.add(rs.getInt("book_id") + " - " + rs.getString("title"));
        }
        rs.close();
        stmt.close();
        return books;
    }

    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}
