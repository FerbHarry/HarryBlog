package com.harry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harry.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-12 09:58:21
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
