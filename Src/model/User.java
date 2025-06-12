package model;
import java.time.LocalDateTime;
public class User {
    private String id;
    private String name;
    private String passwordHash;
    private String email;
    private String facebookId;
    private String role; // "USER" "ADMIN"
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;

    public User (){

    }
    public User(String id, String name, String passwordHash, String email, String facebookId, String role, LocalDateTime createdAt, LocalDateTime lastLogin){
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
        this.facebookId = facebookId;
        this.role = role;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }
    public boolean isFacebookUser (){
        return facebookId != null && !facebookId.isBlank();
    }
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }
    public boolean checkPassword(String rawPassword) {
        // tạm dùng hashCode, nên thay bằng BCrypt
        return passwordHash != null && passwordHash.equals(String.valueOf(rawPassword.hashCode()));
    }
    public void updateLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }

    public String getId() {return id;}
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return facebookId;
    }
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                '}';
    }
}

