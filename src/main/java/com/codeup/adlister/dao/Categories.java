package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;

import java.util.List;

public interface Categories {
    // method will query mysql server for all categories and build a list of Category objects
    List<Category> all();
}
