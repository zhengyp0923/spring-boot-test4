package com.example.demo.realm;

import com.example.demo.dao.PermissionMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.domain.Permission;
import com.example.demo.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 认证     登录 验证用户名密码
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证方法 doGetAuthenticationInfo");
        System.out.println(userMapper);
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取页面的用户名
        String username = token.getUsername();

        User user = userMapper.selectByUsername(username);
        if(user==null){
            //用户不存在
            return null;
        }
        AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        return authenticationInfo;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权 doGetAuthorizationInfo ");
        /**
         * 获取当前用户
         */
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        /**
         * 查询用户所具有的所有的权限
         */
        List<Permission> permissions = permissionMapper.selectPermissionByUserName(user.getUsername());

        /**
         * 添加权限
         */
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        for (Permission permission:permissions){
            simpleAuthorizationInfo.addStringPermission(permission.getName());
        }
        return simpleAuthorizationInfo;
    }

}
