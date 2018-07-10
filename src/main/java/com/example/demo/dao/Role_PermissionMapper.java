package com.example.demo.dao;

import com.example.demo.domain.Role_PermissionKey;

public interface Role_PermissionMapper {
    int deleteByPrimaryKey(Role_PermissionKey key);

    int insert(Role_PermissionKey record);

    int insertSelective(Role_PermissionKey record);

}