package com.harry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.domain.ResponseResult;
import com.harry.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-10-10 15:38:07
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
