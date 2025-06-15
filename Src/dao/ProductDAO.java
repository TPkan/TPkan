package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.DatabaseConnection;
public class ProductDAO {

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Thực hiện truy vấn
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getString("image_url")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(String productId) {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, productId); // Gán ID vào câu truy vấn
            ResultSet rs = stmt.executeQuery();

            // Nếu có sản phẩm, trả về đối tượng Product
            if (rs.next()) {
                product = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getString("image_url")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    // Thêm sản phẩm mới vào cơ sở dữ liệu
    public boolean addProduct(Product product) {
        String query = "INSERT INTO products (name, description, type, price, quantity, created_at, image_url) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getType());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.setTimestamp(6, Timestamp.valueOf(product.getCreatedAt()));
            stmt.setString(7, product.getImageUrl());

            int rowsAffected = stmt.executeUpdate();  // Thực thi câu lệnh
            return rowsAffected > 0;  // Nếu có ít nhất một dòng bị ảnh hưởng thì thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật sản phẩm trong cơ sở dữ liệu
    public boolean updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, description = ?, type = ?, price = ?, quantity = ?, created_at = ?, image_url = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getType());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.setTimestamp(6, Timestamp.valueOf(product.getCreatedAt()));
            stmt.setString(7, product.getImageUrl());
            stmt.setString(8, product.getId());  // Cập nhật theo ID sản phẩm

            int rowsAffected = stmt.executeUpdate();  // Thực thi câu lệnh
            return rowsAffected > 0;  // Nếu có ít nhất một dòng bị ảnh hưởng thì thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sản phẩm khỏi cơ sở dữ liệu
    public boolean deleteProduct(String productId) {
        String query = "DELETE FROM products WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, productId);  // Xóa sản phẩm theo ID

            int rowsAffected = stmt.executeUpdate();  // Thực thi câu lệnh
            return rowsAffected > 0;  // Nếu có ít nhất một dòng bị ảnh hưởng thì thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
