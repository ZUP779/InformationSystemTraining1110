package com.example.esdemo.service;

import com.example.esdemo.model.Film;
import com.example.esdemo.repo.FilmRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Author: ZUP779
 * Date:   2020/4/21 22:20
 * Description:
 */
@Service
public class EsService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private FilmRepository filmRepository;

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

    public List<Film> findFilmsByMultiMatch(String title, String summary, String tags, String actors, double rating){
        if( title == "" && summary == "" && tags == "" && actors == "" && rating == -1)
            return null;

//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//
//        if( title != "" )
//            queryBuilder.withQuery(QueryBuilders.matchQuery("title",title));
//        if( summary !="")
//            queryBuilder.withQuery(QueryBuilders.matchQuery("summary",summary));
//        if( tags != "")
//            queryBuilder.withQuery(QueryBuilders.matchQuery("tags",tags));
//        if( actors != "")
//            queryBuilder.withQuery(QueryBuilders.matchQuery("actors",actors));
//        if( rating != -1)
//            queryBuilder.withQuery(QueryBuilders.matchQuery("rating",rating));
//
//        Iterable<Film> films = filmRepository.search(queryBuilder.build());
        BoolQueryBuilder queryBuilder= QueryBuilders.boolQuery();

        if( title != null) {
            queryBuilder.must(QueryBuilders.matchQuery("title", title));
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
        if( rating != -1) {
            queryBuilder.must(QueryBuilders.matchQuery("rating", rating));
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery,Film.class);
    }
}
