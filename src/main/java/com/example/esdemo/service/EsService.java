package com.example.esdemo.service;

import com.example.esdemo.model.Film;
import com.example.esdemo.repo.FilmRepository;
import org.apache.lucene.search.FuzzyQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Author: ZUP779
 * Date:   2020/4/21 22:20
 * Description:
 */
@Service
public class EsService {

    private Logger logger = LoggerFactory.getLogger(EsService.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private FilmRepository filmRepository;

    @Value("${imageUrlPrefix}")
    String imageUrlPrefix;

    public void createIndex(){
        elasticsearchTemplate.createIndex(Film.class);
        elasticsearchTemplate.putMapping(Film.class);
    }

    public int addCsv2Es(String filePath,boolean hasTitle, String encoding){
        List<Film> films = CsvService.readFromCsv(filePath, hasTitle, encoding);
        filmRepository.saveAll(films);
        return films.size();
    }

    public void deleteFilmById(long id){
        filmRepository.deleteById(id);
    }

    public void addFilm(Film film){
        filmRepository.save(film);
    }

    public List<Film> findFilmsByTitle(String name){
        return filmRepository.findByTitle(name);
    }

    public Iterator<Film> findFilmsMatchTitle(String title){
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);
        Iterable<Film> films = filmRepository.search(queryBuilder);
        return films.iterator();
    }

    public Page<Film> findFilmsByMultiMatch(int page, int size, String title, String summary, String tags, String actors, String author, String location, double lowRating, double toRating){
        if( title == "" && summary == "" && tags == "" && actors == "" && lowRating == -1)
            return null;

        BoolQueryBuilder queryBuilder= QueryBuilders.boolQuery();
//        FuzzyQueryBuilder fuzzyTitle= null;
        if( title != null) {
            queryBuilder.must(QueryBuilders.matchQuery("title", title));
//            fuzzyTitle = QueryBuilders.fuzzyQuery("title",title).fuzziness(Fuzziness.AUTO);
        }
        if( summary != null) {
            queryBuilder.must(QueryBuilders.matchQuery("summary", summary));
        }
        if( tags != null) {
            queryBuilder.must(QueryBuilders.matchQuery("tags", tags));
        }
        if( actors != null) {
            queryBuilder.must(QueryBuilders.matchQuery("actors", actors));
        }
        if( author != null) {
            queryBuilder.must(QueryBuilders.matchQuery("author", author));
        }
        if( location != null) {
            queryBuilder.must(QueryBuilders.matchQuery("location", location));
        }
        if( lowRating != -1 && toRating != -1) {
//            System.out.println(lowRating+" " + toRating);
            queryBuilder.must(QueryBuilders.rangeQuery("rating").from(lowRating).to(toRating));
        }

        Pageable pageable = PageRequest.of(page, size);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
//                .withQuery(fuzzyTitle)
                .withPageable(pageable)
                .build();

        //增加图片Path前缀
        Page<Film> filmPages = elasticsearchTemplate.queryForPage(searchQuery,Film.class);

        for( Film film : filmPages){
            if( !StringUtils.isEmpty(film.getImagePath())) {
                String imagePath = film.getImagePath();
                imagePath = imagePath.replaceFirst("./", "");
                film.setImagePath(imageUrlPrefix + imagePath);
            }
        }
        return filmPages;
    }


    public List<String> getSuggest(String suggestField, String suggestValue){
        Integer suggestMaxCount = 10;

        String suggestName = "FilmSuggest";
        String indexName = "film";
        String typeName = "_doc";

        CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder(suggestField).prefix(suggestValue).size(suggestMaxCount);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion(suggestName, completionSuggestionBuilder);

        SearchRequestBuilder requestBuilder = elasticsearchTemplate.getClient().prepareSearch(indexName).setTypes(typeName).suggest(suggestBuilder);
//        logger.info(requestBuilder.toString());

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

//        suggests.forEach((s)->{
//            logger.info(s);
//        });

        return	 suggests;
    }
}
