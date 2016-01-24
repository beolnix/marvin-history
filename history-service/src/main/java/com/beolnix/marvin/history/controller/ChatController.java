package com.beolnix.marvin.history.controller;


import com.beolnix.marvin.history.api.ChatApi;
import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by beolnix on 23/01/16.
 */
@Api(value = "chats", description = "Chats API")
@RestController
public class ChatController implements ChatApi {

    @ApiOperation("Method returns list of all existing chats.")
    @Override
    public List<ChatDTO> getAllChats() {
        return null;
    }

    @ApiOperation("Method returns chat by name.")
    @Override
    public ChatDTO getChatByName(@ApiParam("name of the chat.") @PathVariable("name") String name) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setName(name);
        return chatDTO;
    }

    @ApiOperation("Method returns chat by id.")
    @Override
    public ChatDTO getChatById(@ApiParam("id of the chat.") @PathVariable("id") Long id) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setName("testname");
        chatDTO.setId(id);
        return chatDTO;
    }

    @ApiOperation("Method creates new chat based on provided model.")
    @Override
    public void createChat(@RequestBody CreateChatDTO createChatDTO) {

    }
}
