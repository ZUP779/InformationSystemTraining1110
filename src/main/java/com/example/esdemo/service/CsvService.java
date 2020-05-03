package com.example.esdemo.service;

import com.example.esdemo.model.Film;
import com.example.esdemo.util.ReadCSV;
import org.elasticsearch.search.aggregations.metrics.weighted_avg.InternalWeightedAvg;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author: ZUP779
 * Date:   2020/4/21 23:46
 * Description:
 */
@Service
public class CsvService {
    public static List<Film> readFromCsv(String filePath,boolean hasTitle, String encoding){
        List<List<String>> list = ReadCSV.readCSV(filePath, hasTitle, encoding);
        List<Film> response = new ArrayList<>();
        for( List<String> row : list){
            int cnt = 1;
            for(String str : row){
                System.out.printf("%d: ",cnt++);
                System.out.println(str);
            }
            System.out.println();
            Film templeFilm = new Film();
            templeFilm.setActors(row.get(0));
            templeFilm.setAnotherName(row.get(1));
            templeFilm.setAuthor(row.get(2));
            templeFilm.setDirector(row.get(3));
            templeFilm.setDuration(Integer.valueOf(row.get(4)));
            templeFilm.setImagePath(row.get(5));
            templeFilm.setImageUrl(row.get(6));
            templeFilm.setLanguage(row.get(7));
            templeFilm.setLocation(row.get(8));
            templeFilm.setMovieType(row.get(9));
            templeFilm.setRating(Double.valueOf(row.get(10)));
            templeFilm.setRatingCount(Integer.valueOf(row.get(11)));
            templeFilm.setReleaseDate(row.get(12));
            templeFilm.setScoreRatio(row.get(13));
            templeFilm.setSummary(row.get(14));
            templeFilm.setTags(row.get(15));
            templeFilm.setTitle(row.get(16));
            templeFilm.setTitleSuggest(row.get(16));
            response.add(templeFilm);
            System.out.println(templeFilm);
        }

        return response;
    }
}
