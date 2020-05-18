package com.example.esdemo.Controller;

import com.example.esdemo.service.EsService;
import io.swagger.annotations.Api;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: ZUP779
 * Date:   2020/4/21 22:24
 * Description:
 */
@Api(hidden = true)
@RestController
public class TestController {

    @Autowired
    private EsService esService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/getTestSuggest")
    public List<String> getSuggest(){
        String suggestField = "name";
        String suggestValue = "奥";
        Integer suggestMaxCount = 10;

        String suggestName = "TestFilmSuggest";
        String indexName = "films";
        String typeName = "_doc";

        CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder(suggestField).prefix(suggestValue).size(suggestMaxCount);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion(suggestName, completionSuggestionBuilder);

        SearchRequestBuilder requestBuilder = elasticsearchTemplate.getClient().prepareSearch(indexName).setTypes(typeName).suggest(suggestBuilder);
        System.out.println(requestBuilder.toString());

        SearchResponse response = requestBuilder.get();
        Suggest suggest = response.getSuggest();

        Set<String> suggestSet = new HashSet<>();
        int maxSuggest = 0;
        if (suggest != null) {
            Suggest.Suggestion result = suggest.getSuggestion(suggestName);//获取suggest,name任意string
            for (Object term : result.getEntries()) {

                if (term instanceof CompletionSuggestion.Entry) {
                    CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;
                    if (!item.getOptions().isEmpty()) {
                        //若item的option不为空,循环遍历
                        for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
                            String tip = option.getText().toString();
                            if (!suggestSet.contains(tip)) {
                                suggestSet.add(tip);
                                ++maxSuggest;
                            }
                        }
                    }
                }
                if (maxSuggest >= suggestMaxCount) {
                    break;
                }
            }
        }

        List<String> suggests = Arrays.asList(suggestSet.toArray(new String[]{}));

        suggests.forEach((s)->{
            System.out.println(s);
        });

		return	 suggests;
    }

    @GetMapping("/create")
    public void testAddData(){
//        Film film1 = new Film(1L, "test1", "test", 1.0, "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
//        Film film2 = new Film(2L, "test2", "test", 1.0, "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
//        esService.addFilm(film1);
//        esService.addFilm(film2);

//        esService.createIndex();
        esService.addCsv2Es("src/main/resources/test.csv",true,"UTF-8");
    }

    @GetMapping("/testRedis")
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("db-type", "redis");
        System.out.println(stringRedisTemplate.opsForValue().get("db-type"));
        System.out.println(redisTemplate.opsForValue().get("db-type"));
    }
}
