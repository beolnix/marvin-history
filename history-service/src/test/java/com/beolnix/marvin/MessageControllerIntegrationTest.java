package com.beolnix.marvin;

import com.beolnix.marvin.history.Application;
import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.model.Message;
import com.beolnix.marvin.history.repository.ChatRepository;
import com.beolnix.marvin.history.repository.MessageRepository;
import com.beolnix.marvin.utils.RestHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by beolnix on 31/01/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest(randomPort = true)
public class MessageControllerIntegrationTest {

    @Value("${local.server.port}")
    private Integer port;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    private String CHAT_NAME = "testChat";
    private RestHelper restHelper;

    private ChatDTO chatDTO;


    @Before
    public void before() {
        chatRepository.deleteAll();
        messageRepository.deleteAll();

        restHelper = new RestHelper(chatRepository, port);
        chatDTO = restHelper.createChat(CHAT_NAME);
    }

    @Test
    public void createMessage() {
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
    }


    private void testRepository(MessageDTO messageDTO) {
        Message message = messageRepository.findOne(messageDTO.getId());

        assertNotNull(message);
        assertEquals(messageDTO.getId(), message.getId());
        assertEquals(message.getChatId(), messageDTO.getChatId());
        assertEquals(message.getTimestamp(), messageDTO.getTimestamp());
        assertEquals(message.getAutor(), messageDTO.getAutor());
        assertEquals(message.getMsg(), messageDTO.getMsg());
    }
}
