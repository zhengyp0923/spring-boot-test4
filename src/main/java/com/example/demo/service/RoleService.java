package com.example.demo.service;

import com.example.demo.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findRoleByUsername(String username);
}
