package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private UserController userController;

    public LoginFrame() {
        userController = new UserController();

        setTitle("Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Đăng nhập");
        JButton registerButton = new JButton("Đăng ký");

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // hoặc giữ lại LoginFrame nếu bạn muốn
                new RegisterFrame();
            }
        });


        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(errorLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        boolean success = userController.login(email, password);
        if (success) {
            errorLabel.setText("");
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            dispose(); // đóng cửa sổ login
            new MainFrame(); // chuyển sang giao diện chính
        } else {
            errorLabel.setText("Email hoặc mật khẩu không đúng.");
        }
    }
}