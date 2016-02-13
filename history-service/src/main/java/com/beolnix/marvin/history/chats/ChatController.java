package com.beolnix.marvin.history.chats;


import com.beolnix.marvin.history.api.ChatApi;
import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by beolnix on 23/01/16.
 */
@RestController
public class ChatController implements ChatApi {

    public final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public List<ChatDTO> getAllChats() {
        return chatService.getChats();
    }


    @Override
    public ChatDTO getChatByName(@PathVariable("name")String name) {
        return chatService.getChatByName(name);
    }

    @Override
    public ChatDTO getChatById(@PathVariable("id") String id) {
        return chatService.getChatById(id);
    }

    @Override
    public ChatDTO createChat(@RequestBody CreateChatDTO createChatDTO) {
        return chatService.createChat(createChatDTO);
    }
}
