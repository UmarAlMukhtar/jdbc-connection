import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LoginGUI {
    private DBHelper db;

    public LoginGUI() {
        try {
            db = new DBHelper();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }

        JFrame frame = new JFrame("Book Exchange Portal - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(3,2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(new JLabel()); // empty
        frame.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            try {
                if (db.checkLogin(username, password)) {
                    showBooks();
                    frame.dispose(); // close login window
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showBooks() throws Exception {
        List<String> books = db.getAllBooks();
        JFrame frame = new JFrame("Book Exchange Portal - Books");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (String book : books) {
            textArea.append(book + "\n");
        }

        frame.add(new JScrollPane(textArea));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}
