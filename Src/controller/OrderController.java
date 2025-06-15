package controller;

import model.Order;
import model.OrderDetail;
import service.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService;

    public OrderController() {
        this.orderService = new OrderService();
    }

    // Tạo đơn hàng (gồm cả danh sách OrderDetail)
    public boolean createOrder(Order order) {
        return orderService.createOrder(order);
    }

    // Lấy danh sách đơn hàng theo user ID
    public List<Order> getOrdersByUserId(String userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // Cập nhật trạng thái đơn hàng (ví dụ: đã giao, đã hủy...)
    public boolean updateOrderStatus(String orderId, String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}