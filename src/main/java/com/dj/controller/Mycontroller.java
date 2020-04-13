package com.dj.controller;

import com.dj.pojo.user;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joker_dj
 * @create 2020-04-13日 17:36
 */
@RestController
public class Mycontroller {

    @ApiOperation("hello控制器的方法")
    @GetMapping({"/","hello"})
    public String hello(){
        return "helloword";
    }

    @ApiOperation("user控制器的方法")
    @PostMapping("/user")
    public user user(){
        return new user();
    }
    @ApiOperation("showUser控制器的方法")
    @PostMapping("showUser")
    public String  showUser(String username,String password){
        //编写业务逻辑 省略
        return "用户名为："+username +"密码为："+password;
    }
}
