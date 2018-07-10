package com.example.demo.controllerAdvice;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PermissionControllerAdvice {

    /**
     * 用户没有权限访问
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(){
        return "/403.html";
    }
}
