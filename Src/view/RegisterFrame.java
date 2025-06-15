package view;

import controller.UserController;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private JLabel errorLabel;
    private UserController userController;

    public RegisterFrame() {
        userController = new UserController();

        setTitle("Đăng ký tài khoản");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        confirmField = new JPasswordField();

        panel.add(labeledField("Họ tên:", nameField));
        panel.add(labeledField("Email:", emailField));
        panel.add(labeledField("Mật khẩu:", passwordField));
        panel.add(labeledField("Nhập lại mật khẩu:", confirmField));

        JButton registerButton = new JButton("Đăng ký");
        registerButton.addActionListener(e -> handleRegister());
        panel.add(registerButton);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(errorLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel labeledField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmField.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Không được để trống thông tin.");
            return;
        }

        if (!password.equals(confirm)) {
            errorLabel.setText("Mật khẩu nhập lại không khớp.");
            return;
        }

        boolean success = userController.register(name, email, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
            dispose(); // Đóng cửa sổ đăng ký
            new LoginFrame(); // Quay lại đăng nhập
        } else {
            errorLabel.setText("Đăng ký thất bại. Email đã được sử dụng?");
        }
    }
}