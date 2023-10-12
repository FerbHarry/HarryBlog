package com.harry.controller;

import com.harry.domain.ResponseResult;
import com.harry.domain.entity.Article;
import com.harry.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章列表")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test(){
//         return articleService.list();
//    }

    @GetMapping("/hotArticleList")
    @ApiOperation(value = "文章列表",notes = "获取热门文章")
    public ResponseResult hotArticleList(){
        ResponseResult result =  articleService.hotArticleList();
        //查询热门文章
        return result;
    }

    @GetMapping("/articleList")
    @ApiOperation(value = "分页文章列表",notes = "获取一页文章")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/article/{id}")
    @ApiOperation(value = "id查询文章",notes = "获取对应id文章")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    @PutMapping("/updateViewCount/{id}")
    @ApiOperation(value = "浏览量",notes = "更新文章浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }
}
