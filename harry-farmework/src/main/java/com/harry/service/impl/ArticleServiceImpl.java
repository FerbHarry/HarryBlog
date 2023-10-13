package com.harry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.harry.constants.SystemConstants;
import com.harry.domain.ResponseResult;
import com.harry.domain.dto.AddArticleDto;
import com.harry.domain.entity.Article;
import com.harry.domain.entity.ArticleTag;
import com.harry.domain.entity.Category;
import com.harry.domain.vo.ArticleDetailVo;
import com.harry.domain.vo.ArticleListVo;
import com.harry.domain.vo.HotArticleVo;
import com.harry.domain.vo.PageVo;
import com.harry.mapper.ArticleMapper;
import com.harry.service.ArticleService;

import com.harry.service.ArticleTagService;
import com.harry.service.CategoryService;
import com.harry.utils.BeanCopyUtils;
import com.harry.utils.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCatch;

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public ResponseResult hotArticleList() {

        //文章信息
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按浏览量降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最高十篇
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);

        List<Article> list = page.getRecords();
        ArrayList<HotArticleVo> hotArticleVos = new ArrayList<>();
        for (Article article: list) {
            HotArticleVo hotArticleVo = new HotArticleVo();
            BeanUtils.copyProperties(article, hotArticleVo);
            hotArticleVos.add(hotArticleVo);
        }


        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态正式发布
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop降序
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        //查询categoryID
       List<Article> articles = page.getRecords();
       articles.stream()
               .map(article ->
                    article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
               .collect(Collectors.toList());


        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {

        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCatch.getCacheMapValue(SystemConstants.REDIS_KEY, id.toString());
        article.setViewCount(Long.valueOf(viewCount));
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();

        Category category = categoryService.getById(categoryId);

        if(category != null){
            articleDetailVo.setCategoryName(category.getName());
        }



        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应 id的浏览量
        redisCatch.incrementCacheMapValue(SystemConstants.REDIS_KEY,id.toString(),1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);


        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }
}
