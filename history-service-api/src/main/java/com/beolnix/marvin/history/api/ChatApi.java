package com.beolnix.marvin.history.api;

import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by beolnix on 23/01/16.
 */
public interface ChatApi {
    @RequestMapping(method = RequestMethod.GET, path = "/chats")
    List<ChatDTO> getAllChats();

    @RequestMapping(method = RequestMethod.GET, path = "/chats/name/{name}")
    ChatDTO getChatByName(@PathVariable("name") String name);

    @RequestMapping(method = RequestMethod.GET, path = "/chats/id/{id}")
    ChatDTO getChatById(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.POST, path = "/chats")
    void createChat(@RequestBody CreateChatDTO createChatDTO);
}
