package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {
    Connection connection;

    // Constructor
    public MySQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Category> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            return createCategories(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public List<Category> categoriesByAdId(long adId) {
        String query = "SELECT * FROM categories AS c" +
                " JOIN ads_categories AS ac ON ac.category_id = c.id" +
                " WHERE ac.ad_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();
            return createCategories(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads by category.", e);
        }
    }

    // method to help extract data from the table categories ResultSet
    private List<Category> createCategories(ResultSet r) throws SQLException {
        List<Category> allCategories = new ArrayList<>();
        while (r.next()) {
            allCategories.add(new Category(
                    r.getLong("id"),
                    r.getString("category_name")
            ));
        }
        return allCategories;
    }
}
