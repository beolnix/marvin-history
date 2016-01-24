package com.beolnix.marvin.history.controller;


import com.beolnix.marvin.history.api.ChatApi;
import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;

import com.beolnix.marvin.history.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public ChatDTO getChatByName(@ApiParam("name of the chat.") @PathVariable("name")String name) {
        return chatService.getChatByName(name);
    }



    @Override
    public ChatDTO getChatById(@ApiParam("id of the chat.") @PathVariable("id") Long id) {
        return chatService.getChatById(id);
    }

    @Override
    public ChatDTO createChat(@RequestBody CreateChatDTO createChatDTO) {
        return chatService.createChat(createChatDTO);
    }
}
