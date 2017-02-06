package com.catchzombie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashsish on 6/2/17.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "USERS")
@JsonIgnoreProperties({"roles"})
public class User extends AbstractPersistable<Long>  {

    /**
     * first name of the user
     */
    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    /**
     * last name of the user
     */
    @NotEmpty
    @Column(nullable = false)
    private String lastName;

    /**
     * email address
     */
    @Email
    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * password of the user (encoded)
     */
    @NotEmpty
    @Column(length = 60)
    private String password;

    /**
     * gender of the user
     */
    @NotEmpty
    @Column(nullable = false)
    private String gender;

    /**
     * List of Role attached to the user
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MAPPING_USER_ROLE")
    private List<Role> roles = new ArrayList<Role>();




}
