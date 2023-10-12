package com.harry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-10-12 09:58:22
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
