package com.catchzombie.services.user.impl;

import com.catchzombie.models.Permission;
import com.catchzombie.models.Role;
import com.catchzombie.models.User;
import com.catchzombie.models.UserCategories;
import com.catchzombie.repositories.UserRepository;
import com.catchzombie.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ashsish on 8/2/17.
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;

    //default role for user USER_ROLE
    Role role = new Role("USER_ROLE");

    //default permission for USER_PERMISSION
    Permission permission = new Permission();

    //Categories to which user can belong
    UserCategories userCategory = new UserCategories();
    Set<UserCategories> userCategorieSet = new HashSet<UserCategories>();
    Set<User> userSet = new HashSet<User>();

    public UserServiceImpl() {
        userCategory.setCategoryName("OTHERS");
        permission.setTitle("USER_PERMISSION");
        permission.setDescription("DEFAULT USER PERMISSION");

    }

    @Override
    public void saveUser(User user) {
        role.setPermissions(Arrays.asList(permission));
        userCategory.getUsers().add(user);
        user.getUserCategories().add(userCategory);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }
}
