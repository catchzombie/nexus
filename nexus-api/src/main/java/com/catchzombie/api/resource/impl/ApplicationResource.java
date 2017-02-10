package com.catchzombie.api.resource.impl;

import com.catchzombie.api.resource.CzBaseResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ashsish on 4/2/17.
 */

@RestController
public class ApplicationResource extends CzBaseResource {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String home(HttpServletRequest request){
        return "Hello, this application is running in ###: " + activeProfile + " :### mode";
    }
}
