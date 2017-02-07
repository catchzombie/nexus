package com.catchzombie;

import com.catchzombie.models.User;
import com.catchzombie.repositories.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;

/**
 * This is is the main security manager class which needs to called by the application for managing sessions.
 * The init method of this class needs to be called once at the start of the application
 * This class will handle creation and deletion of session which will be required by login and logout controllers.
 * It also provides details of currently logged in user as a lightweight user object
 *
 * Created by ashsish on 6/2/17.
 */

@Component
public class CzSecurityManager {

    @Autowired
    UserRepository userRepository;

    public void login(String email, String password) throws AuthenticationException{
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email,password);
        currentUser.login(token); // This will call doGetAuthenticationInfo() of ApplicationRealm
    }

    public void logout(){
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()){
            currentUser.logout();
        }
    }

    public User getLoggedInUser() throws AuthenticationException {
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            throw new AuthenticationException();
        }
        String email = (String) currentUser.getPrincipal();
        return userRepository.findByEmail(email);
    }
}
