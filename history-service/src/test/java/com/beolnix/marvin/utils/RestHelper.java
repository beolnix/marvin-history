package com.beolnix.marvin.utils;

import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;
import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.chats.domain.model.Chat;
import com.beolnix.marvin.history.messages.domain.model.Message;
import com.beolnix.marvin.history.chats.domain.dao.ChatRepository;
import com.beolnix.marvin.history.messages.domain.dao.MessageDAO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by beolnix on 31/01/16.
 */
public class RestHelper {

    private final ChatRepository chatRepository;
    private final MessageDAO messageDAO;
    private final Integer port;

    public RestHelper(ChatRepository chatRepository, MessageDAO messageDAO, Integer port) {
        this.messageDAO = messageDAO;
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

    public MessageDTO createMessage(ChatDTO chatDTO) {
        String baseUrl = "http://localhost:"+port+"/history";
        RestTemplate restTemplate = new RestTemplate();

        CreateMessageDTO createMessageDTO = new CreateMessageDTO();
        createMessageDTO.setAutor("testAutor");
        createMessageDTO.setChatId(chatDTO.getId());
        createMessageDTO.setMsg("testMsg");

        ResponseEntity<MessageDTO> result = restTemplate.postForEntity(baseUrl + "/messages",
                createMessageDTO, MessageDTO.class);

        MessageDTO messageDTO = result.getBody();

        assertNotNull(messageDTO);
        assertNotNull(messageDTO.getId());
        assertEquals(createMessageDTO.getAutor(), messageDTO.getAutor());
        assertEquals(createMessageDTO.getChatId(), messageDTO.getChatId());
        assertEquals(createMessageDTO.getMsg(), messageDTO.getMsg());

        testRepository(messageDTO);

        return messageDTO;
    }

    private void testRepository(MessageDTO messageDTO) {
        Message message = messageDAO.findOne(messageDTO.getId());

        assertNotNull(message);
        assertEquals(messageDTO.getId(), message.getId());
        assertEquals(message.getChatId(), messageDTO.getChatId());
        assertEquals(message.getTimestamp(), messageDTO.getTimestamp());
        assertEquals(message.getAutor(), messageDTO.getAutor());
        assertEquals(message.getMsg(), messageDTO.getMsg());
    }
}
