package com.catchzombie.services.user;

import com.catchzombie.exceptions.UserInformationException;
import com.catchzombie.models.User;

/**
 * Created by ashsish on 7/2/17.
 */
public interface IUserService {

    void saveUser(User user) throws UserInformationException;

    User getUser(String email);

    void deleteUser(String email);

    void updateUser(User user) throws UserInformationException;

}
