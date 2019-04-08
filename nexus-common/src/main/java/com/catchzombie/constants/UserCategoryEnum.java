package com.catchzombie.constants;


import com.sun.tools.javac.code.Attribute;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shubham
 */

public enum UserCategoryEnum {
    SERVICE_SEEKER("seeker"),
    SERVICE_PROVIDER("provider");

    @Getter
    private String category;

    UserCategoryEnum(String category){
        this.category = category;
    }

    public static List<String> getValidCategories(){
        List<String> validCategories = new ArrayList<>();
        Arrays.stream(UserCategoryEnum.values()).forEach(validCategory -> validCategories.add(validCategory.getCategory()));
        return validCategories;
    }

    public static boolean isValidCategory(String category){
        return getValidCategories().contains(category);
    }

    public static void main(String[] args) {
        String str = SERVICE_PROVIDER.toString();
        List<String> ll = new ArrayList<>();
        System.out.println(UserCategoryEnum.isValidCategory("seeker"));
    }
}
