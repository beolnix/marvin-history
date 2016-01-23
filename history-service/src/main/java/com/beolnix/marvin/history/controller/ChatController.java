package com.beolnix.marvin.history.controller;


import com.beolnix.marvin.history.api.ChatApi;
import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

/**
 * Created by beolnix on 23/01/16.
 */
@Controller
public class ChatController implements ChatApi {

    @Override
    public List<ChatDTO> getAllChats() {
        return null;
    }

    @Override
    public ChatDTO getChatByName(@PathVariable("name") String name) {
        return null;
    }

    @Override
    public ChatDTO getChatById(@PathVariable("id") Long id) {
        return null;
    }

    @Override
    public void createChat(@RequestBody CreateChatDTO createChatDTO) {

    }
}
