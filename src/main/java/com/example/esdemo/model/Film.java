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

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Text)
    private String imageUrl;

    @Field(type = FieldType.Double)
    private double score;

    @Field(type = FieldType.Text)
    private String stars;

    @Field(type = FieldType.Text)
    private String tags;

    @Field(type = FieldType.Text)
    private String info;

    @Field(type = FieldType.Text)
    private String director;

    @Field(type = FieldType.Text)
    private String scriptWriters;

    @Field(type = FieldType.Text)
    private String actors;

    @Field(type = FieldType.Text)
    private String type;

    @Field(type = FieldType.Text)
    private String area;

    @Field(type = FieldType.Text)
    private String language;

    @Field(type = FieldType.Text)
    private String date;

    @Field(type = FieldType.Text)
    private String time;

    @Field(type = FieldType.Text)
    private String anotherName;

    public Film() {
    }

    public Film(Long id, String name, String imageUrl, double score, String stars, String tags, String info, String director, String scriptWriters, String actors, String type, String area, String language, String date, String time, String anotherName) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.score = score;
        this.stars = stars;
        this.tags = tags;
        this.info = info;
        this.director = director;
        this.scriptWriters = scriptWriters;
        this.actors = actors;
        this.type = type;
        this.area = area;
        this.language = language;
        this.date = date;
        this.time = time;
        this.anotherName = anotherName;
    }
}
