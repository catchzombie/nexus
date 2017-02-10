package com.catchzombie.api.resource.impl;

import com.catchzombie.CzSecurityManager;
import com.catchzombie.request.impl.LoginRequest;
import com.catchzombie.request.impl.UserRequest;
import com.catchzombie.api.resource.CzBaseResource;
import com.catchzombie.response.CzResponse;
import com.catchzombie.response.ResponseStatus;
import com.catchzombie.services.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

/**
 * Created by ashsish on 8/2/17.
 */

@RestController
@RequestMapping("/user")
public class UserResource extends CzBaseResource{

    private CzSecurityManager czSecurityManager;

    private IUserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileResource.class);

    @Autowired
    public UserResource(CzSecurityManager czSecurityManager, IUserService userService){
        this.czSecurityManager=czSecurityManager;
        this.userService=userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public CzResponse signUp(@RequestBody UserRequest userRequest){
        validateRequest(userRequest);
        try{
            userService.saveUser(userRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        }catch (Exception ex){
            LOGGER.error("unable to sign up ");
            return new CzResponse(ResponseStatus.FAILURE,ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value ="/login")
    public CzResponse login(@RequestBody @Valid LoginRequest loginRequest){
        validateRequest(loginRequest);
        try {
            czSecurityManager.login(loginRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        } catch (AuthenticationException e) {
            LOGGER.error("Unable to login");
            return new CzResponse(ResponseStatus.FAILURE,e.getMessage());
        }
    }

    //TODO:logout method has to take some parameter like username else how the server will know whom to logout . verify
    @RequestMapping(method = RequestMethod.GET,value = "/logout")
    public CzResponse logout(){
        try{
            czSecurityManager.logout();
            return new CzResponse(ResponseStatus.SUCCESS);
        }catch (Exception e){
            LOGGER.error("Unable to logout");
            return new CzResponse(ResponseStatus.FAILURE,e.getMessage());
        }

    }

}
