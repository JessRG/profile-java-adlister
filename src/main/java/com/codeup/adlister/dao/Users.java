package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);
    User updatePassword (String password, User user);
    User updateUsername (String username, User user);
    User updateEmail (String email, User user);
}
