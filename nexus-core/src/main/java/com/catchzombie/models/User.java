package com.catchzombie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ashsish on 6/2/17.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "USERS")
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
     * mobile number of the user limit of 10 digit with values 0-9
     */
    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})")
    @Column(nullable = false)
    private String mobileNo;

    /**
     * url of profile image which will be stored in s3 bucket
     */
    @Column
    private String imageUrl;

    /**
     * Country of user
     */
    @NotEmpty
    @Column(nullable = false)
    private String country;

    /**
     * state of the country to which user belongs
     */
    @NotEmpty
    @Column(nullable = false)
    private String state;


    /**
     * List of Role attached to the user
     */
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "MAPPING_USER_ROLE")
    private List<Role> roles = new ArrayList<Role>();

    /**
     * List of categories to which user belongs to
     */
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "users")
    private Set<UserCategories> userCategories = new HashSet<>();


}
