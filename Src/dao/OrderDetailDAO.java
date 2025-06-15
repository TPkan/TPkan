package dao;

import config.DatabaseConnection;
import model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    public boolean addOrderDetail(OrderDetail detail) {
        String sql = "INSERT INTO order_details (id, order_id, product_id, product_name, unit_price, quantity, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, detail.getId());
            stmt.setInt(2, detail.getOderId());
            stmt.setInt(3, detail.getProductId());
            stmt.setString(4, detail.getProductName());
            stmt.setDouble(5, detail.getUnitPrice());
            stmt.setInt(6, detail.getQuantity());
            stmt.setDouble(7, detail.getTotalPrice());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<OrderDetail> getDetailsByOrderId(String orderId) {
        List<OrderDetail> details = new ArrayList<>();
        String sql = "SELECT * FROM order_details WHERE order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderDetail detail = new OrderDetail(
                        rs.getString("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getString("product_name"),
                        rs.getDouble("unit_price"),
                        rs.getDouble("total_price")
                );
                details.add(detail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }
}