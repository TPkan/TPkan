package main;

import view.LoginFrame;

public class MainApp {
    public static void main(String[] args) {
        // Giao diện Swing nên chạy trong luồng UI
        javax.swing.SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
