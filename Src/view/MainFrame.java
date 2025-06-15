package view;

import controller.ProductController;
import model.Product;
import service.OrderDetailService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainFrame extends JFrame {
    private ProductController productController;
    private OrderDetailService orderDetailService;
    private JTable productTable;
    private JTextField quantityField;
    private JLabel messageLabel;

    public MainFrame() {
        productController = new ProductController();
        orderDetailService = new OrderDetailService();

        setTitle("Danh sách sản phẩm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel header = new JLabel("Sản phẩm Pickleball", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(header, BorderLayout.NORTH);

        productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel thêm vào giỏ
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(new JLabel("Số lượng:"));
        quantityField = new JTextField(5);
        bottomPanel.add(quantityField);

        JButton addToCartButton = new JButton("Thêm vào giỏ hàng");
        addToCartButton.addActionListener(this::handleAddToCart);
        bottomPanel.add(addToCartButton);

        JButton viewCartButton = new JButton("Xem giỏ hàng");
        viewCartButton.addActionListener(e -> new CartFrame(orderDetailService));
        bottomPanel.add(viewCartButton);

        messageLabel = new JLabel("");
        messageLabel.setForeground(new Color(0, 128, 0));
        bottomPanel.add(messageLabel);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        loadProductData();
        getContentPane().add(panel);
        setVisible(true);
    }

    private void loadProductData() {
        List<Product> products = productController.getAllProducts();

        String[] columnNames = {"ID", "Tên", "Mô tả", "Loại", "Giá", "Số lượng"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Product p : products) {
            Object[] row = {
                    p.getId(),
                    p.getName(),
                    p.getDescription(),
                    p.getType(),
                    p.getPrice(),
                    p.getQuantity()
            };
            model.addRow(row);
        }

        productTable.setModel(model);
    }

    private void handleAddToCart(ActionEvent e) {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            messageLabel.setText("Vui lòng chọn sản phẩm.");
            return;
        }

        String quantityText = quantityField.getText();
        if (quantityText.isEmpty() || !quantityText.matches("\\d+")) {
            messageLabel.setText("Số lượng không hợp lệ.");
            return;
        }

        int quantity = Integer.parseInt(quantityText);
        if (quantity <= 0) {
            messageLabel.setText("Số lượng phải lớn hơn 0.");
            return;
        }

        String id = productTable.getValueAt(selectedRow, 0).toString();
        Product product = productController.getProductById(id);

        orderDetailService.addProduct(product, quantity);
        messageLabel.setText("✔ Đã thêm vào giỏ hàng.");
    }
}