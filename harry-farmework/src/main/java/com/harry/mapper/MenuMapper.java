package com.harry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harry.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-12 09:53:13
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);
}
