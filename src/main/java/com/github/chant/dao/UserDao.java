package com.github.chant.dao;


import com.github.chant.entity.User;

public interface UserDao {
    User findByUsername(String username);
}
