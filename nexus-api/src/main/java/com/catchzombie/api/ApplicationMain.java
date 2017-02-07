package com.catchzombie.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by ashsish on 2/2/17.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.catchzombie")
@EntityScan("com.catchzombie.models")
@EnableJpaRepositories("com.catchzombie.repositories")
public class ApplicationMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(ApplicationMain.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class,args);
    }

}
