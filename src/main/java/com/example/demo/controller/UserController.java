package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(String username, String password, HttpServletRequest request) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();  //未认证状态

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            e.printStackTrace();
            return "/login.html";
        }
        User user = (User) subject.getPrincipal();
        request.getSession().setAttribute("login",user);
        return "/index.html";
    }


    @ResponseBody
    @RequestMapping("/findUseById/{id}")
    public User findUseById(@PathVariable Long id) {
        return userService.findUserById(id);
    }


    @RequiresPermissions("user:findByUsername")
    @ResponseBody
    @RequestMapping("/findByUsername/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
}
