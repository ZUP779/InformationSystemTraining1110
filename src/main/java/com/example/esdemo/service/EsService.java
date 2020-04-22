package com.example.esdemo.service;

import com.example.esdemo.model.Film;
import com.example.esdemo.repo.FilmRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
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

    public List<Film> findFilmsByName(String name){
        return filmRepository.findByName(name);
    }

    public Iterator<Film> findFilmsMatchName(String name){
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);
        Iterable<Film> films = filmRepository.search(queryBuilder);
        return films.iterator();
    }
}
