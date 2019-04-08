package com.catchzombie.constants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shubham
 */
public enum ValidCountryEnum {
    INDIA("India");

    @Getter
    private String country;

    ValidCountryEnum(String country){
        this.country = country;
    }

    public static List<String> getValidCountries(){
        List<String> validCountries = new ArrayList<>();
        Arrays.stream(ValidCountryEnum.values()).forEach(validCountry -> validCountries.add(validCountry.getCountry()));
        return validCountries;
    }

    public static boolean isValidCountry(String country){
        return getValidCountries().contains(country);
    }

}
