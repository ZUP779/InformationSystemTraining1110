package com.example.esdemo.Controller;

import com.example.esdemo.model.Film;
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
    public void testCreateIndex(){
        esService.createIndex();
    }

    @GetMapping("/create")
    public void testAddData(){
//        Film film1 = new Film(1L, "test1", "test", 1.0, "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
//        Film film2 = new Film(2L, "test2", "test", 1.0, "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
//        esService.addFilm(film1);
//        esService.addFilm(film2);
        esService.addCsv2Es("src/main/resources/test.csv",true,"UTF-8");
    }
}
