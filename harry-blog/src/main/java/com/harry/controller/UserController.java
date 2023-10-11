package com.harry.controller;


import com.harry.annotation.SystemLog;
import com.harry.domain.ResponseResult;
import com.harry.domain.entity.User;
import com.harry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @SystemLog(businessName = "获得用户信息")
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @SystemLog(businessName = "更新用户信息")
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
    @SystemLog(businessName = "用户注册")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

}
