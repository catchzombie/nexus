package com.catchzombie.api.resource.impl;

import com.catchzombie.services.chat.ChatService;
import com.catchzombie.request.impl.ChatRequest;
import com.catchzombie.api.resource.CzBaseResource;
import com.catchzombie.response.*;
import com.catchzombie.response.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shubham on 4/2/17.
 */
@RestController
@RequestMapping("/chat")
public class ChatResource extends CzBaseResource {

    private final ChatService chatService;

    private static final Logger LOGGER= LoggerFactory.getLogger(ChatResource.class);

    @Autowired
    public ChatResource(ChatService chatService) {
        this.chatService = chatService;
    }


    @RequestMapping(method = RequestMethod.POST,value = "/chatSend")
    public CzResponse sendChat(@RequestBody ChatRequest chatRequest){
        validateRequest(chatRequest);
        LOGGER.info(String.format("Received request to send chat:" , chatRequest));
        try{
            chatService.sendChat(chatRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            LOGGER.error("Unable to send the chat",e);
            return new CzResponse(ResponseStatus.FAILURE,e.getMessage());
        }

    }

    @RequestMapping("/getChat")
    public CzResponse getChat(@RequestParam String recepientId){
        validateNonNullRequestParams(recepientId);
        validateNonEmptyRequestParams(recepientId);
        LOGGER.info(String.format("Received request to get chat for user" ,recepientId));
        try{
            return new CzResponse(ResponseStatus.SUCCESS,chatService.getChat(recepientId));
        }catch (Exception e){
            LOGGER.error("Unable to get the chat",e);
            return new CzResponse(ResponseStatus.FAILURE,e.getMessage());
        }
    }
}
