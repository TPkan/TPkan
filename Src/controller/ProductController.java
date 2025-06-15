package controller;

import model.Product;
import service.ProductService;
import java.lang.String;
import java.util.List;

public class ProductController {
    private ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(String id) {
        return productService.getProductById(id);
    }

    // Thêm sản phẩm (dành cho admin)
    public boolean addProduct(Product product) {
        return productService.addProduct(product);
    }

    // Cập nhật sản phẩm (dành cho admin)
    public boolean updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    // Xóa sản phẩm (dành cho admin)
    public boolean deleteProduct(String id) {
        return productService.deleteProduct(id);
    }
}