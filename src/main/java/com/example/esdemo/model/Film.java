package com.example.esdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Author: ZUP779
 * Date:   2020/4/21 20:29
 * Description:
 */
@Data
@Document(indexName = "film", type = "docs")
public class Film {
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;

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

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String tags;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String summary;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String director;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String author;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String actors;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String movieType;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String location;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String language;

    @Field(type = FieldType.Text)
    private String releaseDate;

    @Field(type = FieldType.Integer)
    private int duration;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String anotherName;

    public Film() {
    }

    public Film(Long id, String title, String imageUrl, String imagePath, double rating, int ratingCount, String scoreRatio, String tags, String summary, String director, String author, String actors, String movieType, String location, String language,  String releaseDate, int duration, String anotherName) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.imagePath = imagePath;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.scoreRatio = scoreRatio;
        this.tags = tags;
        this.summary = summary;
        this.director = director;
        this.author = author;
        this.actors = actors;
        this.movieType = movieType;
        this.location = location;
        this.language = language;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.anotherName = anotherName;
    }
}
