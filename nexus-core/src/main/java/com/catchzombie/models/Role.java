package com.catchzombie.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashsish on 6/2/17.
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "USERS_ROLE")
public class Role extends AbstractPersistable<Long> {

    /**
     * Name of the role
     */
    @NotEmpty
    @Column(unique = true,nullable = false)
    private String roleName;

    /**
     * List of Permission attached to the role
     */
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "MAPPING_ROLE_PERMISSION",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name="permission_id")})
    private List<Permission> permissions = new ArrayList<Permission>();

    /**
     *
     * @param roleName The name of the role
     */

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
