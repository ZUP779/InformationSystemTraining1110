package com.example.esdemo.Controller;

import com.example.esdemo.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: ZUP779
 * Date:   2020/4/21 22:24
 * Description:
 */
@RestController
public class TestController {
    @Autowired
    EsService esService;

    @GetMapping("/test")
    public void testCreate(){
        System.out.println("test");
        esService.createIndex();
    }

}
