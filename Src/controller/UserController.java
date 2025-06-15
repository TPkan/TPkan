package controller;
import java.util.UUID;
import model.User;
import service.UserService;
import java.time.LocalDateTime;

public class UserController {
    private UserService userService;
    private User currentUser;

    public UserController() {
        this.userService = new UserService();
    }

    // Xử lý đăng nhập
    public boolean login(String email, String password) {
        User user = userService.login(email, password);
        if (user != null) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

    // Xử lý đăng ký
    public boolean register(User user) {
        return userService.register(user);
    }

    // Lấy thông tin người dùng hiện tại (sau khi đăng nhập)
    public User getCurrentUser() {
        return currentUser;
    }

    // Đăng xuất
    public void logout() {
        this.currentUser = null;
    }
    public boolean register(String name, String email, String password) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(String.valueOf(password.hashCode()));
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        return userService.register(user);  // hoặc gọi DAO nếu không có UserService
    }

}