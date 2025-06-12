package model;
import java.time.LocalDateTime;
public class Product {
    private String id;
    private String name;
    private String description;
    private String type ;
    private double price;
    private int quantity;
    private LocalDateTime createdAt;
    private String imageUrl;

    public Product(){

    }
    public Product(String id, String name, String description, String type, double price, int quantity, LocalDateTime createdAt, String imageUrl){
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public String getId(){return id;}
    public void setId(String id) {this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getType(){return type;}
    public void setType(String type){this.type = type;}

    public double getPrice(){return price;}
    public void setPrice(double price){this.price = price;}

    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public String getImageUrl() {return imageUrl;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
    public boolean isAvailable(){
        return quantity > 0;
    }
}
