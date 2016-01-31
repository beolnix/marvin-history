package com.beolnix.marvin;

import com.beolnix.marvin.history.Application;
import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.chats.domain.dao.ChatRepository;
import com.beolnix.marvin.history.messages.domain.dao.MessageDAO;
import com.beolnix.marvin.adapters.PageImplBean;
import com.beolnix.marvin.utils.RestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    private MessageDAO messageDAO;

    private String CHAT_NAME = "testChat";
    private RestHelper restHelper;

    private ChatDTO chatDTO;


    @Before
    public void before() {
        chatRepository.deleteAll();
        messageDAO.deleteAll();

        restHelper = new RestHelper(chatRepository, messageDAO, port);
        chatDTO = restHelper.createChat(CHAT_NAME);
    }

    @Test
    public void createMessage() {
        restHelper.createMessage(chatDTO);
    }

    @Test
    public void getMessages() {
        for (int i = 0; i < 20; i++) {
            restHelper.createMessage(chatDTO);
        }

        String baseUrl = "http://localhost:"+port+"/history";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PageImplBean<MessageDTO>> response = restTemplate.exchange(baseUrl + "/messages?chatId=" + chatDTO.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageImplBean<MessageDTO>>() {
        });

        Page<MessageDTO> page = response.getBody();

        assertNotNull(page);
        assertEquals(20, page.getSize());
        assertEquals(1, page.getTotalPages());
        assertEquals(new Sort(Sort.Direction.DESC, "timestamp"), page.getSort());
        assertEquals(20, page.getContent().size());
    }

}
