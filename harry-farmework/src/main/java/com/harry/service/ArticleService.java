package com.harry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.domain.ResponseResult;
import com.harry.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
