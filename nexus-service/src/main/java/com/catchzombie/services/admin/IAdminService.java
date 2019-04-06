package com.catchzombie.services.admin;

/**
 * @author shubham
 */

public interface IAdminService {

    // Profession of user
    boolean addRole(String role);

    boolean deleteRole(String role);

    // Service seeker or provider
    boolean addUserCategory(String userCategory);

    boolean deleteUserCategory(String userCategory);


}
