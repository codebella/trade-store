package com.sample.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class WebApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(WebApplication.class, args);
        Object port = context.getEnvironment().getProperty("server.port");
        System.out.println("Swagger URL is here: " + getBaseUrl(port));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Trade Store API",
                "API for carrying out operation on Trade Store",
                "v1",
                "",
                new Contact("Soni Kumari", "", ""),
                "", "", Collections.emptyList());
    }

    public static String getBaseUrl(Object port) throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostName();

        return "http://" + ip + ":" + port + "/swagger-ui.html";
    }
}
