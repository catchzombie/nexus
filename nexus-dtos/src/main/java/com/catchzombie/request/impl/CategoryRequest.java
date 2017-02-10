package com.catchzombie.request.impl;

import com.catchzombie.request.CzBaseRequest;
import com.catchzombie.utils.Tuple;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;

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
public class CategoryRequest extends CzBaseRequest {

    private String userId;

    private Map<String,String> providerCategoryMap;

    private Map<String,String> seekerCategoryMap;





    @Override
    public Tuple<Boolean, String> valid() {
    if(userId.equals(null)||userId.isEmpty()){
            return new Tuple<>(false, "Null/Empty userId");
        }
        return new Tuple<>(true,null);
    }
}
