package com.catchzombie.models;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashsish on 6/2/17.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends AbstractPersistable<Integer> {

    /**
     * Name of the role. Default is general for seekers
     */
    @NotEmpty
    @Column(unique = true,nullable = false, columnDefinition="default 'general'")
    private String roleName;

//    /**
//     * List of Permission attached to the role
//     */
//    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinTable(name = "MAPPING_ROLE_PERMISSION",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name="permission_id")})
//    private List<Permission> permissions = new ArrayList<Permission>();

}
