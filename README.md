
# Swagger2简介

> 1.随项目自动生成强大RESTful API文档，减少工作量
>2.API文档与代码整合在一起，便于同步更新API说明
>3.页面测试功能来调试每个RESTful API
# springboot集成Swagger2步骤
## 1. 新建一个Springboot项目
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413172803261.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
## 2. 导入依赖
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413173746130.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
```java
		  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
		 <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
```

## 3. 编写控制器
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413173904330.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
@RestController
public class Mycontroller {

    @GetMapping({"/","hello"})
    public String hello(){
        return "helloword";
    }
}
```
## 4. 编写swagger的配置类
写上注解
@Configuration
@EnableSwagger2
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413174016240.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
}
```
启动项目运行
浏览器输入 http://localhost:8080/swagger-ui.html
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413174253293.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
我们所写的controller方法都会被检测到
## 5. 配置Swagger2
1. 配置Swagger信息 info
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413174818420.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apinfo());
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
```

运行看效果

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413174847234.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
2. 配置多个分组
多配置几个Docket即可
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041317502716.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413175231457.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
3. 配置其他的信息 不一一解释 都有注释
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020041317534746.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
@Bean
    public Docket docket(){

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
```
## 6.添加注释信息
1. 编写user实体
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413175732764.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
2. 在controller返回一个user
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413175823193.png)
运行
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413175910642.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
会检测到我们所有写的controller方法 
也可以进行测试方法
点击Try it out
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413180005847.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
3. 点击运行
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413180054515.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
这里就会返回我们测试接口的信息以及响应的结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413180138691.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
4. 给类，属性和方法添加注释信息
在user实体类添加注解
@ApiModel：给类添加注释
@ApiModelProperty ：给字段添加注释
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413181052886.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)

```java
@ApiModel("用户实体类")
public class user {
    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;
}
```

运行看效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413181305754.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
添加到controller上
 @ApiOperation
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413181426439.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
运行看效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413181513667.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
5. 发送数据 测试接口
在controller中编写代码
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413181741615.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
运行测试
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413181829488.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
输入用户名 密码 点击测试
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413181912885.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200413181950977.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pva2VyZGoyMzM=,size_16,color_FFFFFF,t_70)
这样就返回了我们数据

总结：Swagger这个工具主要是针对开发人员测试接口来使用的，
注意：在项目上线的时候己得吧Swagger给关闭 防止接口暴露 （安全）
