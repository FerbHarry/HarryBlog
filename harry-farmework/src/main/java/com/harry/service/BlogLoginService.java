package com.harry.service;

import com.harry.domain.ResponseResult;
import com.harry.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
