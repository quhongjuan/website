package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int uid;
    private String username;
    private String password;
    private String email;


    public User() {
    }

    public User(ResultSet rs) throws SQLException {
        uid=rs.getInt("uid");
        username=rs.getString("USERNAME");
        password=rs.getString("PASSWORD");
        email=rs.getString("email");
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
