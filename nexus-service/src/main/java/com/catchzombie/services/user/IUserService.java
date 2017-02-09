package com.catchzombie.services.user;

import com.catchzombie.models.User;

/**
 * Created by ashsish on 7/2/17.
 */
public interface IUserService {

    void saveUser(User user);

    User getUser(String email);

}
