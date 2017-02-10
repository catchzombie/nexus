package com.catchzombie.dtos.user;

import com.catchzombie.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by shubham on 11/2/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO  {

    private User user;

}
