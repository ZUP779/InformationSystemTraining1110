package com.example.esdemo.repo;

import com.example.esdemo.model.Film;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Author: ZUP779
 * Date:   2020/4/21 23:40
 * Description:
 */
public interface FilmRepository extends ElasticsearchRepository<Film,Long> {
    List<Film> findByTitle(String title);
}
