package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 获取当前用户的所有的角色
     * @param session
     * @return
     */
    @RequestMapping("/findRoleByUsername")
    public List<String> findRoleByUsername(HttpSession session) {
        User user = (User) session.getAttribute("login");
        List<Role> roles = roleService.findRoleByUsername(user.getUsername());
        List<String> roleNames = null;
        if (roles != null || roles.size() > 0) {
            roleNames = new ArrayList<>();
            for (Role role : roles) {
                roleNames.add(role.getName());
            }
        }
        return roleNames;
    }
}
