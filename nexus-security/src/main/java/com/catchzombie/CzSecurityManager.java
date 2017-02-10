package com.catchzombie;

import com.catchzombie.constants.SessionParams;
import com.catchzombie.models.User;
import com.catchzombie.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    private static Logger logger = LoggerFactory.getLogger(CzSecurityManager.class);

    public String encryptPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            return new BigInteger(1, crypt.digest()).toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("Exception in encrypt password", e);
        }
        return null;
    }

    public void login(String email, String password, HttpServletRequest request) throws AuthenticationException{
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email,password);
        currentUser.login(token); // This will call doGetAuthenticationInfo() of ApplicationRealm
        setLegacySessionInfo(request);
        logger.info("Logged in [userId = " + email + "]");
    }

    public void logout() throws AuthenticationException {
        Subject currentUser = SecurityUtils.getSubject();
        User user = getLoggedInUser();
        if (currentUser.isAuthenticated()){
            currentUser.logout();
            logger.info("Logged out [userId = " + user.getEmail() + "]");
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

    private void setLegacySessionInfo(HttpServletRequest request) throws AuthenticationException {
        HttpSession session = request.getSession(true);
        User user = getLoggedInUser();
        session.setAttribute(SessionParams.EMAIL,user.getEmail());
        session.setAttribute(SessionParams.CONTACT_NUMBER,user.getMobileNo());
        session.setAttribute(SessionParams.IMAGE_URL,user.getImageUrl());
        session.setAttribute(SessionParams.ROLES, StringUtils.join(user.getRoles(),","));
        session.setAttribute(SessionParams.USER_CATEGORIES,StringUtils.join(user.getUserCategories(),","));
    }
}
