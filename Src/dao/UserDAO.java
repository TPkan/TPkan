package dao;
import model.User;
import java.sql.*;
import config.DatabaseConnection;
public class UserDAO {
    public User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password_hard"),
                        rs.getString("email"),
                        rs.getString("facebook_id"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toLocalDateTime() : null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User authenticateUser(String email, String passwordHash) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ? AND password_hash = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password_hash"),
                        rs.getString("email"),
                        rs.getString("facebook_id"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toLocalDateTime() : null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean createUser(User user) {
        String sql = "INSERT INTO users (id, name, password_hash, email, facebook_id, role, created_at, last_login) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getFacebookId());
            stmt.setString(6, user.getRole());
            stmt.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            stmt.setTimestamp(8, user.getLastLogin() != null ? Timestamp.valueOf(user.getLastLogin()) : null);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserById(String id) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password_hash"),
                        rs.getString("email"),
                        rs.getString("facebook_id"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toLocalDateTime() : null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
