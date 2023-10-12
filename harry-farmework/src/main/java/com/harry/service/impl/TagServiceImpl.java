package com.harry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.domain.entity.Tag;
import com.harry.mapper.TagMapper;
import com.harry.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-10-12 09:15:18
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
