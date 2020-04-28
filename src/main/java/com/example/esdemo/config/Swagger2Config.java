package com.example.esdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.text.Document;

/**
 * Author: ZUP779
 * Date:   2020/4/27 21:05
 * Description:
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // 指定api类型为swagger2
                .apiInfo(apiInfo())                 // 用于定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.example.esdemo"))   // 指定controller包
                .paths(PathSelectors.any())         // 所有controller
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("esdemo api")        // 文档页标题
                .contact(new Contact("zup779",
                        "https://www.bonjour.com",
                        "abc@bupt.edu.cn"))        // 联系人信息
                .description("esdemo restful api文档")  // 详细信息
                .version("1.0.0")   // 文档版本号
                .termsOfServiceUrl("https://www.bonjour.com") // 网站地址
                .build();
    }
}
