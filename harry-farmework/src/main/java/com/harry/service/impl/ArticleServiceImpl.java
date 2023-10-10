package com.harry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.domain.ResponseResult;
import com.harry.domain.entity.Article;
import com.harry.mapper.ArticleMapper;
import com.harry.service.ArticleService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult hotArticleList() {

        //文章信息
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus,0);
        //按浏览量降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最高十篇
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);

        List<Article> list = page.getRecords();

        return ResponseResult.okResult(list);
    }
}
