package com.example.esdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.completion.Completion;

import java.io.Serializable;

/**
 * Author: ZUP779
 * Date:   2020/4/21 20:29
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "film", type = "_doc")
public class Film  implements Serializable {
    private static final long serialVersionUID = 779L;

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String titleSuggest;

//    @CompletionField(analyzer="ik_max_word",searchAnalyzer="ik_max_word")
//    private Completion title;

    @Field(type = FieldType.Text)
    private String imageUrl;

    @Field(type = FieldType.Text)
    private String imagePath;

    @Field(type = FieldType.Double)
    private double rating;

    @Field(type = FieldType.Integer)
    private int ratingCount;

    @Field(type = FieldType.Text)
    private String scoreRatio;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String tags;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String summary;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String director;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String author;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String actors;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String movieType;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String location;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String language;

    @Field(type = FieldType.Text)
    private String releaseDate;

    @Field(type = FieldType.Integer)
    private int duration;

    @Field(type = FieldType.Auto, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String anotherName;

}
/*
*
{
  "mappings": {
    "properties": {
      "id": {
        "type": "long"
      },
      "title": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "titleSuggest": {
        "type": "completion",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "imageUrl": {
        "type": "text"
      },
      "imagePath": {
        "type": "text"
      },
      "rating": {
        "type": "double"
      },
      "ratingCount": {
        "type": "integer"
      },
      "scoreRatio": {
        "type": "text"
      },
      "tags": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "summary": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "director": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "author": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "actors": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "movieType": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "location": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "language": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "releaseDate": {
        "type": "text"
      },
      "duration": {
        "type": "integer"
      },
      "anotherName": {
        "type": "text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      }
    }
  }
}
*
* */