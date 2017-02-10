package com.catchzombie.request.impl;

import com.catchzombie.request.CzBaseRequest;
import com.catchzombie.dtos.user.UserRequestDTO;
import com.catchzombie.utils.Tuple;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Created by shubham on 11/2/17.
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest extends CzBaseRequest {

    private UserRequestDTO userRequestDTO;

    @Override
    public Tuple<Boolean, String> valid() {
        if(userRequestDTO.getUser().equals(null)){
            return new Tuple<>(false, "Null user details");
        }
        return new Tuple<>(true,null);
    }
}
