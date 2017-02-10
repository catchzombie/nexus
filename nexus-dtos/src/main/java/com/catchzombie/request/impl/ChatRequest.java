package com.catchzombie.request.impl;

import com.catchzombie.dtos.chat.ChatDTO;
import com.catchzombie.request.CzBaseRequest;
import com.catchzombie.utils.Tuple;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
public class ChatRequest extends CzBaseRequest {

    private ChatDTO chatDTO;


    @Override
    public Tuple<Boolean, String> valid() {
        if(chatDTO.getMessage().equals(null)|| chatDTO.getMessage().isEmpty()|| chatDTO.getRecepientId().equals(null)|| chatDTO.getRecepientId().isEmpty()|| chatDTO.getSenderId().equals(null)|| chatDTO.getSenderId().isEmpty()){
            return new Tuple<>(false, "Null/Empty senderId/recepientId/message");
        }
        return new Tuple<>(true,null);
    }
}
