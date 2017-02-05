package com.catchzombie;

import com.catchzombie.chat.ChatDTO;
import com.catchzombie.request.impl.ChatRequest;
import com.catchzombie.response.ResponseStatus;
import org.springframework.stereotype.Service;

/**
 * Created by shubham on 4/2/17.
 */
@Service
public class ChatService {
    public ResponseStatus sendChat(ChatRequest chatRequest) {
        return ResponseStatus.SUCCESS;
    }

    public ChatDTO getChat(String recepientId) {
        return null;
    }
}
