package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pickle_ball"; // URL của cơ sở dữ liệu
    private static final String USER = "root";  // Tên người dùng MySQL
    private static final String PASSWORD = "12032005"; // Mật khẩu của MySQL

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connection failed");
        }
    }
}
