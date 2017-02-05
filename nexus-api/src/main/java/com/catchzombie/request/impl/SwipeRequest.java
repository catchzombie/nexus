package com.catchzombie.request.impl;

import com.catchzombie.request.CzBaseRequest;
import com.catchzombie.utils.Tuple;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Set;

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
public class SwipeRequest extends CzBaseRequest{

//TODO

    @Override
    public Tuple<Boolean, String> valid() {
        return null;
    }
}
