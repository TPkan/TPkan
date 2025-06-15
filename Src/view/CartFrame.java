package view;

import model.Order;
import model.OrderDetail;
import service.OrderDetailService;
import service.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CartFrame extends JFrame {
    private JTable cartTable;
    private JLabel totalLabel;
    private OrderDetailService orderDetailService;
    private OrderService orderService;

    public CartFrame(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
        this.orderService = new OrderService();

        setTitle("Giỏ hàng của bạn");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel header = new JLabel("Giỏ hàng", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(header, BorderLayout.NORTH);

        cartTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(cartTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel("Tổng tiền: 0 đ");
        JButton checkoutButton = new JButton("Đặt hàng");
        JButton clearButton = new JButton("Xóa giỏ hàng");

        checkoutButton.addActionListener(e -> handleCheckout());
        clearButton.addActionListener(e -> {
            orderDetailService.clearOrderDetails();
            loadCartData();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(totalLabel);
        bottomPanel.add(clearButton);
        bottomPanel.add(checkoutButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        loadCartData();
        getContentPane().add(panel);
        setVisible(true);
    }

    private void loadCartData() {
        List<OrderDetail> details = orderDetailService.getOrderDetails();

        String[] columnNames = {"Sản phẩm", "Số lượng", "Đơn giá", "Tổng"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (OrderDetail d : details) {
            Object[] row = {
                    d.getProductName(),
                    d.getQuantity(),
                    d.getUnitPrice(),
                    d.getTotalPrice()
            };
            model.addRow(row);
        }

        cartTable.setModel(model);
        totalLabel.setText("Tổng tiền: " + orderDetailService.getTotalAmount() + " đ");
    }

    private void handleCheckout() {
        List<OrderDetail> details = orderDetailService.getOrderDetails();
        if (details.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng đang trống.");
            return;
        }

        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId("demo_user"); // TODO: lấy userId thực tế nếu đã đăng nhập
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderDetails(details);
        order.setTotalAmount(orderDetailService.getTotalAmount());

        boolean success = orderService.createOrder(order);
        if (success) {
            JOptionPane.showMessageDialog(this, "Đặt hàng thành công!");
            orderDetailService.clearOrderDetails();
            loadCartData();
        } else {
            JOptionPane.showMessageDialog(this, "Đặt hàng thất bại. Vui lòng thử lại.");
        }
    }
}