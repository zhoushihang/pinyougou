package com.hanjixin.core.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆管理
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    //获取当前登陆人
    @RequestMapping("/name")
    public Map<String,Object> showName(HttpServletRequest request){
/*        //1:办法1 通过Session
        SecurityContext spring_security_context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        User principal = (User) spring_security_context.getAuthentication().getPrincipal();
        String username = principal.getUsername();

        //2:办法2
        User principal1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();*/

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,Object> map = new HashMap<>();
        map.put("loginName",name);
        return map;
    }
}
