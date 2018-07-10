package com.example.demo.service.impl;

import com.example.demo.dao.RoleMapper;
import com.example.demo.domain.Role;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }
}
