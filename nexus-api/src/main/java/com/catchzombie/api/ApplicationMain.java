package com.catchzombie.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by ashsish on 2/2/17.
 */
@SpringBootApplication
public class ApplicationMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(ApplicationMain.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class,args);
    }

}
