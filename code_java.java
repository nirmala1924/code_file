import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VulnerableCodeExample {

    // SQL Injection Vulnerability
    public static void getUserData(String userInput) throws SQLException {
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "username", "password");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            System.out.println("Username: " + username + ", Email: " + email);
        }
    }

    // Cross-Site Scripting (XSS) Vulnerability
    public static void displayUserInput(String userText) {
        System.out.println("<div>" + userText + "</div>");
    }

    // Insecure Direct Object Reference (IDOR) Vulnerability
    public static void getUserProfile(String userId) {
        if (userId.equals("admin")) {
            System.out.println("Admin profile accessed.");
        } else {
            System.out.println("Regular user profile accessed.");
        }
    }

    // Insecure Deserialization Vulnerability
    public static void deserializeData(String fileName) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            Object object = inputStream.readObject();
            System.out.println("Deserialized data: " + object.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // SQL Injection Vulnerability
            getUserData("admin' OR '1'='1");

            // Cross-Site Scripting (XSS) Vulnerability
            String userInput = "<script>alert('XSS Attack');</script>";
            displayUserInput(userInput);

            // Insecure Direct Object Reference (IDOR) Vulnerability
            String userId = "admin";
            getUserProfile(userId);

            // Insecure Deserialization Vulnerability
            String fileName = "maliciousData.ser";
            deserializeData(fileName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
