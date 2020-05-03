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
@Document(indexName = "film", type = "docs")
public class Film  implements Serializable {
    private static final long serialVersionUID = 779L;

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;

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

}
