package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {
    Connection connection;

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
