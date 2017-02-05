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
public class ProfileMinorDetails {

    @Id
    @GeneratedValue
    private long id;

    private long userId;

    private String userName;

    private String country;

    private String state;

    private String city;

    private boolean isServiceProvider;

    private boolean isServiceSeeker;

    private long profileCreatedAt;

    private long lastLoginTime;





}
