package com.catchzombie.services.user.impl;

import com.catchzombie.constants.UserCategoryEnum;
import com.catchzombie.constants.ValidCountryEnum;
import com.catchzombie.constants.ValidStateEnum;
import com.catchzombie.exceptions.UserInformationException;
import com.catchzombie.models.Role;
import com.catchzombie.models.User;
import com.catchzombie.repositories.RoleRepository;
import com.catchzombie.repositories.UserRepository;
import com.catchzombie.services.user.IUserService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.catchzombie.constants.Constants.DEFAULT_USER_ROLE;

/**
 * @author shubham
 */
@Service
@NoArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void saveUser(User user) throws UserInformationException {
        String userCategory = user.getUserCategory().getCategoryName();
        if (null != userCategory && UserCategoryEnum.isValidCategory(userCategory)){
            if(null == user.getRoles() || user.getRoles().isEmpty()){
                user.setRoles(new ArrayList<Role>(){{add(new Role(DEFAULT_USER_ROLE));}});
            }
            userRepository.save(user);
        }
        else throw new UserInformationException("Error in category field");

    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(String email) {
        User user = getUser(email);
        userRepository.delete(user);
    }

    @Override
    public void updateUser(User user) throws UserInformationException {
        if( null == user.getEmail() || user.getEmail().isEmpty())
            throw new UserInformationException("Email is null or empty");
        User originalUser = getUser(user.getEmail());
        if(null == originalUser){ // As the record doesn't exist so creating new record.
            saveUser(user);
            return;
        }
        if (null!=user.getFirstName() && !user.getFirstName().isEmpty() && !user.getFirstName().equals(originalUser.getFirstName()))
            throw new UserInformationException("FirstName can't be empty or changed");
        if (null!=user.getLastName() && !user.getLastName().isEmpty() && !user.getLastName().equals(originalUser.getLastName()))
            throw new UserInformationException("Lastname can't be empty or changed");
            if (null != user.getRoles() && !user.getRoles().isEmpty())
            originalUser.setRoles(user.getRoles());
        if (null != user.getBioData()) // Biodata can be empty so no need for empty check
            originalUser.setBioData(user.getBioData());
        if (null != user.getCountry() && !user.getCountry().isEmpty() && ValidCountryEnum.isValidCountry(user.getCountry()))
            originalUser.setCountry(user.getCountry());
        if (null != user.getGender() && !user.getGender().isEmpty())
            originalUser.setGender(user.getGender());
        if (null != user.getImageUrl() && !user.getImageUrl().isEmpty())
            originalUser.setImageUrl(user.getImageUrl());
        if (null != user.getMobileNo() && !user.getMobileNo().isEmpty())
            originalUser.setMobileNo(user.getMobileNo());
        if (null != user.getPassword() && !user.getPassword().isEmpty())
            originalUser.setPassword(user.getPassword());
        if (null != user.getState() && !user.getState().isEmpty() && ValidStateEnum.isValidState(user.getState()))
            originalUser.setState(user.getState());
        userRepository.save(originalUser);
    }

}
