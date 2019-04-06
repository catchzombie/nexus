package com.catchzombie.constants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shubham
 */
public enum ValidStateEnum {
    KARNATAKA("Karnataka",ValidCountryEnum.INDIA.getCountry());

    @Getter
    private String state;

    @Getter
    private ValidCountryEnum country;

    ValidStateEnum(String state,String country){
        this.state = state;
        this.country = ValidCountryEnum.valueOf(country);
    }

    public static List<String> getValidStates(){
        List<String> validStates = new ArrayList<>();
        Arrays.stream(ValidStateEnum.values()).forEach(validState -> validStates.add(validState.getState()));
        return validStates;
    }

    public static boolean isValidState(String state){
        return getValidStates().contains(state);
    }
}
