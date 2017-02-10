package com.catchzombie.request.impl;

import com.catchzombie.dtos.profile.ProfileDTO;
import com.catchzombie.request.CzBaseRequest;
import com.catchzombie.utils.Tuple;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Created by shubham on 5/2/17.
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileRequest extends CzBaseRequest {

    private ProfileDTO profileDTO;

    /**
     * Note: Profile image and bio can be null . If image is null then some default image is applied.
     * @return
     */

    @Override
    public Tuple<Boolean, String> valid() {
        if(profileDTO.getProfileMajorDetails().equals(null)|| profileDTO.getProfileMinorDetails().equals(null)){
            return new Tuple<>(false, "Null/Empty profileMajorDetails/profileMinorDetails");
        }
        return new Tuple<>(true,null);
    }
    }

