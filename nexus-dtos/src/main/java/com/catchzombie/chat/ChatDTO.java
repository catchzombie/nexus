package com.catchzombie.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by shubham on 5/2/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private String senderId;

    private String recepientId;

    private String message;

    public ChatDTO(String senderId, String recepientId){
        this.senderId=senderId;
        this.recepientId=recepientId;
    }
}
