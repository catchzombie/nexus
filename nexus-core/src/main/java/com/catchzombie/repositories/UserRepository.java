package com.catchzombie.repositories;

import com.catchzombie.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ashsish on 6/2/17.
 */
public interface UserRepository extends CrudRepository<User,Long> {

    public User findByEmail(String email);
}
