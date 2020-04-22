package com.example.esdemo.Controller;

import com.example.esdemo.model.Film;
import com.example.esdemo.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

/**
 * Author: ZUP779
 * Date:   2020/4/22 15:36
 * Description:
 */
@RestController
public class EsController {

    @Autowired
    private EsService esService;

    @GetMapping("/matchName")
    public Iterator<Film> findFilmsMatchName(@RequestParam String name){
        return esService.findFilmsMatchName(name);
    }

}
