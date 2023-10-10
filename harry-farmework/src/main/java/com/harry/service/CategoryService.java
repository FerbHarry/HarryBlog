package com.harry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.domain.ResponseResult;
import com.harry.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-10-10 14:15:13
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
