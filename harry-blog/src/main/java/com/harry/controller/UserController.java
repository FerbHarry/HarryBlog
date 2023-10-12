package com.harry.controller;


import com.harry.annotation.SystemLog;
import com.harry.domain.ResponseResult;
import com.harry.domain.entity.User;
import com.harry.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @SystemLog(businessName = "获得用户信息")
    @GetMapping("/userInfo")
    @ApiOperation(value = "用户信息",notes = "获得用户信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @SystemLog(businessName = "更新用户信息")
    @PutMapping("/userInfo")
    @ApiOperation(value = "更新信息",notes = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
    @SystemLog(businessName = "用户注册")
    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

}
