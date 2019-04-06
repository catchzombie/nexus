package com.catchzombie.user;

import javax.validation.constraints.NotNull;

/**
 * Created by ashsish on 8/2/17.
 */
public class LoginRequestDto {

    @NotNull
    String email;

    @NotNull
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
