package com.catchzombie.dtos.profile;

import com.catchzombie.aerospike.impl.ProfileBio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by shubham on 5/2/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private ProfileMajorDetails profileMajorDetails;

    private ProfileMinorDetails profileMinorDetails;

    private ProfileBio profileBio;



}
