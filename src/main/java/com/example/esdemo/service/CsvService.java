package com.example.esdemo.service;

import com.example.esdemo.model.Film;
import com.example.esdemo.util.ReadCSV;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ZUP779
 * Date:   2020/4/21 23:46
 * Description:
 */
@Service
public class CsvService {
    //TODO： 使用反射取代hard code
    public static List<Film> readFromCsv(String filePath,boolean hasTitle, String encoding){
        List<List<String>> list = ReadCSV.readCSV(filePath, hasTitle, encoding);
        List<Film> response = new ArrayList<>();
        for( List<String> row : list){
//            Film templeFilm = new Film();
//            templeFilm.set
        }

        return response;
    }
}
