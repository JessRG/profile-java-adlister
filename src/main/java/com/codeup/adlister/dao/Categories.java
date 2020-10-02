package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;

import java.util.List;

public interface Categories {
    // method to get list of all categories
    List<Category> all();

    // method to get categories by ad id
    List<Category> categoriesByAdId(long adId);
}
