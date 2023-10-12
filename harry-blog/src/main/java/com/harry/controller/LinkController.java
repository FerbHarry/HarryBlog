package com.harry.controller;


import com.harry.domain.ResponseResult;
import com.harry.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
@Api(tags = "友链")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @ApiOperation(value = "友链列表",notes = "获取一页友链")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }

}
