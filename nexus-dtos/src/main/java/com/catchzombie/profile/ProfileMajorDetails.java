package com.catchzombie.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by shubham on 5/2/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfileMajorDetails {
    @Id
    @GeneratedValue
    private Long userId;

    private String firstName;

    private String lastName;

    private Long contactNumber;

    private String emailId;

    private String passwordHash;


}
