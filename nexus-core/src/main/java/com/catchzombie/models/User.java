package com.catchzombie.models;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

/**
 * Created by ashsish on 6/2/17.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "service_seeker")
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
     * biodata of the user
     */
    @Column(length = 300)
    private String bioData;


    /**
     * List of roles. For provider, it represents the skillset of the user while for seeker it represents skillset required.
     * For seekers, roles can be empty at signup and a default 'general' role would be provided
     */
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_mapping" , joinColumns = @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            ))
    private List<Role> roles = new ArrayList<Role>();

    /**
     * Category to which user belongs to i.e seeker or provider.
     */
    @JoinTable(name = "user_category_mapping", joinColumns = @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"
            ))
    @OneToOne
    private Category userCategory;

}
