package com.harry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.domain.entity.User;
import com.harry.mapper.UserMapper;
import com.harry.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-10-11 10:38:39
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
