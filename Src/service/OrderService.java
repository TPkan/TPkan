package service;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import model.Order;
import model.OrderDetail;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.orderDetailDAO = new OrderDetailDAO();
    }

    public boolean createOrder(Order order) {
        boolean orderCreated = orderDAO.createOrder(order);
        if (!orderCreated) {
            return false;
        }

        boolean allDetailsAdded = true;
        for (OrderDetail detail : order.getOrderDetails()) {
            boolean added = orderDetailDAO.addOrderDetail(detail);
            if (!added) {
                allDetailsAdded = false;
            }
        }

        return allDetailsAdded;
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderDAO.getOrdersByUserId(userId);
    }

    public boolean updateOrderStatus(String orderId, String status) {
        return orderDAO.updateOrderStatus(orderId, status);
    }
}