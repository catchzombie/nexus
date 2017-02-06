package com.catchzombie.repositories;

import com.catchzombie.models.Permission;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ashsish on 6/2/17.
 */
public interface PermissionRepository extends CrudRepository<Permission,Long> {
}
