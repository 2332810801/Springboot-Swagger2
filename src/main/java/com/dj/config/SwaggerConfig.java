package com.dj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author joker_dj
 * @create 2020-04-13日 17:39
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置多个分组
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apinfo())
                .enable(true)
                .groupName("docket1");

    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apinfo())
                .enable(true)
                .groupName("docket2");

    }


    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apinfo())
                //是否启用swagger false不启用
                .enable(true)
                .groupName("joker_dj")
                .select()
                /*
                配置RequestHandlerSelectors 扫描接口的方式
                指定扫描的包basePackage
                any()扫描全部
                none()不扫描
                withClassAnnotation：扫描类上的注解
                */
                .apis(RequestHandlerSelectors.basePackage("com.dj.controller"))
                //过滤路径 比如PathSelectors.ant("login/**")
                // .paths(PathSelectors.ant(""))
                .build();
    }
    public ApiInfo apinfo(){
        Contact contact = new Contact(
                "joker_dj",
                "https://www.cnblogs.com/joker-dj/",
                "2332810801@qq.com");
        return new ApiInfo("joker_dj的api文档",
                "Api Documentation",
                "1.0",
                "https://www.cnblogs.com/joker-dj/",
                contact, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
