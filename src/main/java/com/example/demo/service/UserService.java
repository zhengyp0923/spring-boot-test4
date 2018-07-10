package com.example.demo.service;

import com.example.demo.domain.User;

public interface UserService {
    User findUserById(Long id);
    User findUserByUsername(String username);
    boolean  login(String username,String password);
}
