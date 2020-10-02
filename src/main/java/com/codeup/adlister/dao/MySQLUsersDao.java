package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    Config.getUrl(),
                    Config.getUser(),
                    Config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }


    //======================== NEEDED METHODS FOR UPDATES =========================

    //------------------------------ show all users -------------------------------
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        String query = "Select * from users";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    //------------------------------  update method  ------------------------------
    private User update(String query, String info_to_update, User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, info_to_update);
            stmt.setLong(2, user.getId());
            stmt.executeUpdate();
            return findById((int) user.getId());

        } catch (SQLException e) {
            throw new RuntimeException("Issue updating user information.");
        }
    }

    //------------------------------  insert user ---------------------------------
    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    //------------------------------  extract user --------------------------------
    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    //------------------------  finding user through ID  ------------------------
    public User findById(int Id) {
        String query = "Select * from users where id = ? Limit 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, Id);
            ResultSet rs = stmt.executeQuery();
            return extractUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Problem identifying the user");
        }
    }

    //---------------------  finding user through username  ----------------------
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    //============================== UPDATING TABLE ==============================

    //----------------------------- update  password -----------------------------
    @Override
    public User updatePassword(String password, User user) {
        String query = "UPDATE users SET password = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            password = Password.hash(password);
            stmt.setString(1, password);
            stmt.setLong(2, user.getId());
            int user_id = stmt.executeUpdate();
            return findById(user_id);

        } catch (SQLException e) {
            throw new RuntimeException("Issue updating password");
        }
    }

    //------------------------------ update  username -----------------------------
    @Override
    public User updateUsername(String username, User user) {
        String query = "UPDATE users SET username = ? WHERE id = ?";
        return update(query, username, user);
    }

    //------------------------------   update email -------------------------------
    @Override
    public User updateEmail(String email, User user) {
        String query = "UPDATE users SET email = ? WHERE id = ?";
        return update(query, email, user);
    }


    //------------------------------   delete user   -----------------------------
    public void deleteUser(User user) {
        String sql = "DELETE FROM users WHERE username = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

