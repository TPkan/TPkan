package service;

import dao.UserDAO;
import model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User login(String email, String rawPassword) {
        String hashedPassword = String.valueOf(rawPassword.hashCode());
        return userDAO.authenticateUser(email, hashedPassword);
    }

    public boolean register(User user) {
        if (userDAO.getUserByEmail(user.getEmail()) != null) {
            return false; // Email đã tồn tại
        }
        user.setPasswordHash(String.valueOf(user.getPasswordHash().hashCode()));
        return userDAO.createUser(user);
    }

    public User getUserById(String id) {
        return userDAO.getUserById(id);
    }

    public boolean isEmailTaken(String email) {
        return userDAO.getUserByEmail(email) != null;
    }
}