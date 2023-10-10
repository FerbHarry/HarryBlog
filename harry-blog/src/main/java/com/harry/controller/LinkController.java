package com.harry.controller;


import com.harry.domain.ResponseResult;
import com.harry.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }

}
