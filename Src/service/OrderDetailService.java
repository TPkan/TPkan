package service;

import model.OrderDetail;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailService {
    private List<OrderDetail> orderDetails;

    public OrderDetailService() {
        this.orderDetails = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        for (OrderDetail item : orderDetails) {
            if (item.getProductId() == Integer.parseInt(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                item.setTotalPrice(item.getQuantity() * item.getUnitPrice());
                return;
            }
        }
        OrderDetail detail = new OrderDetail();
        detail.setProductId(Integer.parseInt(product.getId()));
        detail.setProductName(product.getName());
        detail.setUnitPrice(product.getPrice());
        detail.setQuantity(quantity);
        detail.setTotalPrice(product.getPrice() * quantity);
        orderDetails.add(detail);
    }

    public void removeProduct(int productId) {
        orderDetails.removeIf(item -> item.getProductId() == productId);
    }

    public void clearOrderDetails() {
        orderDetails.clear();
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderDetail item : orderDetails) {
            total += item.getTotalPrice();
        }
        return total;
    }
}