package com.harry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.domain.ResponseResult;
import com.harry.domain.dto.TagListDto;
import com.harry.domain.entity.Tag;
import com.harry.domain.vo.PageVo;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-10-12 09:15:17
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);
}
