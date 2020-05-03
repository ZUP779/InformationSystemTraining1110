package com.example.esdemo.Controller;

import com.example.esdemo.model.Film;
import com.example.esdemo.service.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * Author: ZUP779
 * Date:   2020/4/22 15:36
 * Description:
 */
@RestController
@RequestMapping
@Api(value = "ES Resful API 接口", tags = "ES Restful API 接口")
public class EsController {

    @Autowired
    private EsService esService;

//    @GetMapping("/matchTitle")
//    public Iterator<Film> findFilmsMatchTitle(@RequestParam String title){
//        return esService.findFilmsMatchTitle(title);
//    }
//    @GetMapping("/findTitle")
//    public List<Film> findFilmsByTitle(@RequestParam String title){
//        return esService.findFilmsByTitle(title);
//    }

//        queryBuilder.withQuery(QueryBuilders.matchQuery("title",film.getTitle()));
//        queryBuilder.withQuery(QueryBuilders.matchQuery("summary",film.getSummary()));
//        queryBuilder.withQuery(QueryBuilders.matchQuery("tags",film.getTags()));
//        queryBuilder.withQuery(QueryBuilders.matchQuery("actors",film.getActors()));
//        queryBuilder.withQuery(QueryBuilders.matchQuery("rating",film.getRating()));

    @ApiOperation(value = "根据相关信息查找符合的film", httpMethod = "GET", notes = "根据相关信息查找符合的film")
    @GetMapping("/match")
    public List<Film> findFilms(@ApiParam(name = "title", value = "电影名", required = false) @RequestParam(value = "title", required = false) String title,
                                @ApiParam(name = "summary", value = "电影简介", required = false) @RequestParam(value = "summary", required = false) String summary,
                                @ApiParam(name = "tags", value = "电影标签", required = false) @RequestParam(value = "tags", required = false) String tags,
                                @ApiParam(name = "actors", value = "演员", required = false) @RequestParam(value = "actors", required = false) String actors,
                                @ApiParam(name = "rating", value = "电影评分", required = false)@RequestParam(value = "rating", required = false, defaultValue = "-1") String rating){
//        System.out.println(title);
//        System.out.println(summary);
//        System.out.println(tags);
//        System.out.println(actors);
//        System.out.println(rating);
        double ratingDouble = -1;
        ratingDouble = Double.valueOf(rating);
        return esService.findFilmsByMultiMatch(title, summary, tags, actors, ratingDouble);
    }

    @ApiOperation(value = "返回Suggest", httpMethod = "GET", notes = "根据用户输入的值返回Suggest，目前仅支持对titleSuggest字段的suggest")
    @GetMapping("/getSuggest")
    public List<String> getSuggest(@ApiParam(name = "suggestField", value = "suggest字段名", required = true, example = "titleSuggest") @RequestParam(value = "suggestField", required = true) String suggestField,
                                   @ApiParam(name = "suggestValue", value = "用户输入的值", required = true, example = "隐形") @RequestParam(value = "suggestValue", required = true)String suggestValue){
        return esService.getSuggest(suggestField,suggestValue);
    }
}
