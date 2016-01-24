package com.beolnix.marvin.history.api;

import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient("history")
@RequestMapping("history")
public interface ChatApi {
    @RequestMapping(method = RequestMethod.GET, value = "/chats")
    List<ChatDTO> getAllChats();

    @RequestMapping(method = RequestMethod.GET, value = "/chats/name/{name}")
    ChatDTO getChatByName(@PathVariable("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/chats/id/{id}")
    ChatDTO getChatById(
            @PathVariable("id") Long id
    );

    @RequestMapping(method = RequestMethod.POST, value = "/chats")
    void createChat(
            @RequestBody CreateChatDTO createChatDTO
    );
}
