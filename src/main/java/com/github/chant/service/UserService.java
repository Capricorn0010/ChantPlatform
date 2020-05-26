package com.github.chant.service;

import com.github.chant.entity.User;

public interface UserService {

    User findByUsername(String username);
}
