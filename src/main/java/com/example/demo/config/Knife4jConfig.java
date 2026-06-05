package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("敏感数据加密示例 API")
                        .description("Spring Boot + MyBatis Plus 敏感字段自动加密解密")
                        .version("1.0.0")
                        .contact(new Contact().name("demo")));
    }
}
