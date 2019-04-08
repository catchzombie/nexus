package com.catchzombie.api.controllers.user;

import com.catchzombie.CzSecurityManager;
import com.catchzombie.models.User;
import com.catchzombie.services.user.IUserService;
import com.catchzombie.user.LoginRequestDto;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author shubham
 */

@RestController
@RequestMapping("/v1/user")
public class UserSignupController {
    @Autowired
    private CzSecurityManager czSecurityManager;

    @Autowired
    private IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(com.catchzombie.api.controllers.user.ServiceSeeker.class);

    @ApiOperation("Signup to the website")
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Void> signUp(@RequestBody User user){
        try{
            userService.saveUser(user);
        }catch (Exception ex){
            logger.error("Exception occured while signup",ex.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Login to the website")
    @RequestMapping(method = RequestMethod.POST, value ="/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequestDto loginRequest, HttpServletRequest request){
        try {
            czSecurityManager.login(loginRequest.getEmail(),loginRequest.getPassword(),request);
        } catch (AuthenticationException e) {
            logger.error("Exception occured whiled login",e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Logout from the website")
    @RequestMapping(method = RequestMethod.GET,value = "/logout")
    public ResponseEntity<Void> logout(){
        try {
            czSecurityManager.logout();
        } catch (AuthenticationException e) {
            logger.error("Exception occured while logout",e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation("Update info")
    @RequestMapping(method = RequestMethod.PUT, value = "/updateInfo")
    public ResponseEntity<Void> updateInfo(@RequestBody User user){
        try{
            userService.updateUser(user);
        }catch (Exception ex){
            logger.error("Exception occured while update",ex.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Delete user")
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam @NotNull String email){
        try{
            userService.deleteUser(email);
        }catch (Exception ex){
            logger.error("Exception occured while deleting user",ex.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
