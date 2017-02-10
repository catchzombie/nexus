package com.catchzombie.api.controllers.user;

import com.catchzombie.CzSecurityManager;
import com.catchzombie.models.User;
import com.catchzombie.services.user.IUserService;
import com.catchzombie.user.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by ashsish on 8/2/17.
 */

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private CzSecurityManager czSecurityManager;

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Void> signUp(@RequestBody User user){
        try{
            userService.saveUser(user);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value ="/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequestDto loginRequest, HttpServletRequest request){
        try {
            czSecurityManager.login(loginRequest.getUsername(),loginRequest.getPassword(),request);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/logout")
    public ResponseEntity<Void> logout(){
        try {
            czSecurityManager.logout();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
