package com.catchzombie.services.user.impl;

import com.catchzombie.constants.UserCategoryEnum;
import com.catchzombie.models.*;
import com.catchzombie.repositories.RoleRepository;
import com.catchzombie.repositories.UserRepository;
import com.catchzombie.services.user.IUserService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.catchzombie.constants.Constants.DEFAULT_USER_ROLE;

/**
 * Created by ashsish on 8/2/17.
 */
@Service
@NoArgsConstructor
public class ServiceSeekerImpl{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(ServiceSeekerImpl.class);

}
