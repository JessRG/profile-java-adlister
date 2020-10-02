package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    // method to get the user's info from given id
    User getUserInfo(long userId);

    // method to get the ad info from given id
    Ad getAdInfo(long adId);

    //method to get the category info from given id
    void setAdCategories(long adId, long catId);

    List<Ad> search(String searchTerm);

    //Overload
    List<Ad> search(long catId);
}
