package com.example.esdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Author: ZUP779
 * Date:   2020/5/2 17:52
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "films",type = "_doc")
public class TestFilm {
    @Id
    @Field(type = FieldType.Auto)
    private String name;

    @Field(type = FieldType.Auto)
    private String desc;


}
