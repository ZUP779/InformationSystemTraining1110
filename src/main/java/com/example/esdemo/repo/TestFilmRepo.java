package com.example.esdemo.repo;

import com.example.esdemo.model.TestFilm;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * Author: ZUP779
 * Date:   2020/5/3 11:22
 * Description:
 */
public interface TestFilmRepo extends ElasticsearchCrudRepository<TestFilm, String> {

}
