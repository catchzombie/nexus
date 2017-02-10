package com.catchzombie.services.user;

import com.catchzombie.models.User;
import com.catchzombie.request.impl.UserRequest;

/**
 * Created by ashsish on 7/2/17.
 */
public interface IUserService {

    void saveUser(UserRequest userRequest);

    User getUser(String email);

}
