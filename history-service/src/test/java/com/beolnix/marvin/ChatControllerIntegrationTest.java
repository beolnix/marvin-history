package com.beolnix.marvin;

import com.beolnix.marvin.history.Application;

import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;
import com.beolnix.marvin.history.chats.domain.dao.ChatDAO;
import com.beolnix.marvin.history.messages.domain.dao.MessageDAO;
import com.beolnix.marvin.utils.HeaderRequestInterceptor;
import com.beolnix.marvin.utils.RestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest(randomPort = true)
public class ChatControllerIntegrationTest {

    @Value("${local.server.port}")
    private Integer port;

    @Autowired
    private ChatDAO chatDAO;

    @Autowired
    private MessageDAO messageDAO;

    private String CHAT_NAME = "testChat";
    private RestHelper restHelper;
    private List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();

    public ChatControllerIntegrationTest() {
        interceptors.add(new HeaderRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));
    }

    @Before
    public void before() {
        chatDAO.deleteAll();
        restHelper = new RestHelper(chatDAO, messageDAO, port);
    }

    @Test
    public void createChat() {
        // given
        String baseUrl = "http://localhost:"+port+"/history";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(interceptors);
        CreateChatDTO createChatDTO = new CreateChatDTO();
        createChatDTO.setConference(true);
        createChatDTO.setName(CHAT_NAME);
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

        restHelper.testRepository(chatDTO);
    }

    @Test
    public void getChatsTest() {
        createChat();

        List<ChatDTO> chats = restHelper.getChats();

        assertTrue(chats.size() == 1);
    }

    @Test
    public void getChatByNameTest() {
        createChat();

        ChatDTO chatDTO = restHelper.getChatByName(CHAT_NAME);

        assertNotNull(chatDTO);
        assertNotNull(chatDTO.getId());

        assertEquals(CHAT_NAME, chatDTO.getName());
    }



}
