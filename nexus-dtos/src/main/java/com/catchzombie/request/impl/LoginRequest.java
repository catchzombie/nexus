package com.catchzombie.request.impl;

import com.catchzombie.request.CzBaseRequest;
import com.catchzombie.dtos.user.LoginRequestDTO;
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
public class LoginRequest extends CzBaseRequest{

    private LoginRequestDTO loginRequestDTO;

    @Override
    public Tuple<Boolean, String> valid() {
        if(loginRequestDTO.getUsername().equals(null)||loginRequestDTO.getUsername().isEmpty()||loginRequestDTO.getPassword().equals(null)||loginRequestDTO.getPassword().isEmpty()){
            return new Tuple<>(false, "Null/Empty username/password");
        }
        return new Tuple<>(true,null);
    }
    }

