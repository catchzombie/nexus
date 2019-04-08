package com.catchzombie.services.admin.impl;

import com.catchzombie.repositories.UserRepository;
import com.catchzombie.services.admin.IAdminService;
import com.catchzombie.services.user.impl.ServiceSeekerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shubham
 */
public class AdminServiceImpl implements IAdminService {
    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ServiceSeekerImpl.class);
    @Override
    public boolean addRole(String role) {
        return false;
    }

    @Override
    public boolean deleteRole(String role) {
        return false;
    }

    @Override
    public boolean addUserCategory(String userCategory) {
        return false;
    }

    @Override
    public boolean deleteUserCategory(String userCategory) {
        return false;
    }
}
