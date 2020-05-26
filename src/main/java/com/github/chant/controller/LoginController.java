package com.github.chant.controller;

import com.github.chant.util.JWTUtil;
import com.github.chant.util.ResultMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class LoginController {

    @Autowired
    private ResultMap resultMap;

    //用户登录
    @PostMapping("/login")
    public ResultMap loginUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session) {
        //把前端输入的username和password封装为token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            session.setAttribute("user", subject.getPrincipal());
            return resultMap.success().code(200).message(JWTUtil.createToken(username));
        } catch (UnknownAccountException | IncorrectCredentialsException uae) {
            return resultMap.fail().code(401).message("用户名或密码错误");
        } catch (LockedAccountException lae) {
            return resultMap.fail().code(401).message("用户账号被锁定不可用");
        } catch (AuthenticationException ae) {
            return resultMap.fail().code(401).message(ae.getMessage());
        }
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public ResultMap unauthorized(@PathVariable String message) {
        return resultMap.success().code(401).message(message);
    }

    //admin角色才能访问
    @RequestMapping("/admin")
    @RequiresRoles(logical = Logical.OR, value = {"admin"})
    @ResponseBody
    public String admin() {
        return "admin success";
    }
}
