package org.example.controller;

import com.harry.domain.ResponseResult;
import com.harry.domain.entity.LoginUser;
import com.harry.domain.entity.Menu;
import com.harry.domain.entity.User;
import com.harry.domain.vo.AdminUserInfoVo;
import com.harry.domain.vo.RoutersVo;
import com.harry.domain.vo.UserInfoVo;
import com.harry.enums.AppHttpCodeEnum;
import com.harry.exception.SystemException;
import com.harry.service.LoginService;
import com.harry.service.MenuService;
import com.harry.service.RoleService;
import com.harry.utils.BeanCopyUtils;
import com.harry.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查角色信息
        List<String>roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);

        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        //需要用户ID
        Long userId = SecurityUtils.getUserId();
        //查询menu
       List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);

        return ResponseResult.okResult(new RoutersVo(menus));

    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }


}