package model;

public class OrderDetail {
    private String id;           // Mã dòng đơn hàng
    private int orderId;      // Mã đơn hàng cha
    private int productId;    // Mã sản phẩm
    private String productName; // Tên sản phẩm (có thể cache lại)
    private double unitPrice; // Giá từng sản phẩm lúc đặt
    private int quantity;     // Số lượng mua
    private double totalPrice;// Tổng tiền dòng này

    public OrderDetail(){
    }
    public OrderDetail(String id, int orderId, int productId, int quantity, String productName, double unitPrice, double totalPrice){
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }
    public double calculateTotalPrice(){
        return unitPrice * quantity;
    }

    public String getId(){return id;}
    public void setId(String id) {this.id = id;}

    public int getOderId(){return orderId;}
    public void setOrderId(int orderId){this.orderId = orderId;}

    public int getProductId(){return productId;}
    public void setProductId(int productId){this.productId = productId;}

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}

    public double getUnitPrice(){return unitPrice;}
    public void setUnitPrice(double unitPrice){this.unitPrice = unitPrice;}

    public double getTotalPrice(){return totalPrice;}
    public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}

    public String getProductName(){return productName;}
    public void setProductName(String productName){this.productName=productName;}

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id='" + id + '\'' +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
