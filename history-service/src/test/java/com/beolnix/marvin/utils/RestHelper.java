package com.beolnix.marvin.utils;

import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;
import com.beolnix.marvin.history.model.Chat;
import com.beolnix.marvin.history.repository.ChatRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by beolnix on 31/01/16.
 */
public class RestHelper {

    private final ChatRepository chatRepository;
    private final Integer port;

    public RestHelper(ChatRepository chatRepository, Integer port) {
        this.chatRepository = chatRepository;
        this.port = port;
    }

    public void testRepository(ChatDTO chatDTO) {
        Chat chat = chatRepository.findOne(chatDTO.getId());

        assertNotNull(chat);

        assertEquals(chatDTO.getName(), chat.getName());
        assertEquals(chatDTO.getProtocol(), chat.getProtocol());
        assertEquals(chatDTO.getConference(), chat.getConference());
    }

    public ChatDTO getChatByName(String name) {
        String baseUrl = "http://localhost:"+port+"/history";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ChatDTO> result = restTemplate.getForEntity(baseUrl + "/chats/name/" + name, ChatDTO.class);
        return result.getBody();
    }

    public List<ChatDTO> getChats() {
        String baseUrl = "http://localhost:"+port+"/history";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ChatDTO>> chatsResponse =
                restTemplate.exchange(baseUrl + "/chats", HttpMethod.GET, null, new ParameterizedTypeReference<List<ChatDTO>>() {
                });
        return chatsResponse.getBody();
    }


    public ChatDTO createChat(String chatName) {
        // given
        String baseUrl = "http://localhost:"+port+"/history";
        RestTemplate restTemplate = new RestTemplate();

        CreateChatDTO createChatDTO = new CreateChatDTO();
        createChatDTO.setConference(true);
        createChatDTO.setName(chatName);
        createChatDTO.setProtocol("SKYPE");

        // when
        ResponseEntity<ChatDTO> result = restTemplate.postForEntity(baseUrl + "/chats", createChatDTO, ChatDTO.class);
        ChatDTO chatDTO = result.getBody();

        // then
        assertNotNull(chatDTO);
        assertNotNull(chatDTO.getId());
        assertEquals(createChatDTO.getConference(), chatDTO.getConference());
        assertEquals(createChatDTO.getName(), chatDTO.getName());
        assertEquals(createChatDTO.getProtocol(), chatDTO.getProtocol());

        testRepository(chatDTO);

        return chatDTO;
    }
}
