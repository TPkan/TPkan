package model;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class Order {
    private String id;
    private String userId;
    private String status; // trạng thái đơn hàng
    private LocalDateTime createdAt; // Thời gian đặt đơn
    private double totalAmount; // tổng tiền đơn hàng
    private List<OrderDetail> orderDetails;

    public Order(){

    }
    public Order(String id, String userId, String status, LocalDateTime createdAt, double totalAmount, List orderDetails){
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.orderDetails = orderDetails;
    }

    public String getId(){return id;}
    public void setId(String id) {this.id = id;}

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    public List<OrderDetail> getOrderDetails() {return orderDetails;}
    public void setOrderDetails(List<OrderDetail> orderDetails) {this.orderDetails = orderDetails;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", totalAmount=" + totalAmount +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

