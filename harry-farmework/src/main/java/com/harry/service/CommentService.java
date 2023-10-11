package com.harry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.domain.ResponseResult;
import com.harry.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-10-11 08:38:05
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
