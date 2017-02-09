package com.catchzombie.user;

import javax.validation.constraints.NotNull;

/**
 * Created by ashsish on 8/2/17.
 */
public class LoginRequestDto {

    @NotNull
    String username;

    @NotNull
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
