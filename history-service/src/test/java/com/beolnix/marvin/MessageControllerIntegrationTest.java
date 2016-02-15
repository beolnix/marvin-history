package com.beolnix.marvin;

import com.beolnix.marvin.history.Application;
import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.chats.domain.dao.ChatDAO;
import com.beolnix.marvin.history.messages.domain.dao.MessageDAO;
import com.beolnix.marvin.adapters.PageImplBean;
import com.beolnix.marvin.utils.HeaderRequestInterceptor;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

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
    private ChatDAO chatDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.auth}")
    private String apiAuth;

    private String CHAT_NAME = "testChat";
    private RestHelper restHelper;

    private ChatDTO chatDTO;

    private String baseUrl;

    @Before
    public void before() {
        chatDAO.deleteAll();
        messageDAO.deleteAll();

        restHelper = new RestHelper(chatDAO, messageDAO, port, apiKey, apiAuth);
        chatDTO = restHelper.createChat(CHAT_NAME);
        baseUrl = "http://localhost:"+port+"/api/v1/";
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

        RestTemplate restTemplate = restHelper.getRestTemplate();
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

    @Test
    public void getMessagesScroll() {
        // given = 40 messages
        for (int i = 0; i < 40; i++) {
            restHelper.createMessage(chatDTO);
        }

        // initial request for the history
        // must return the latest 20 messages
        Page<MessageDTO> page = restHelper.getMessages(chatDTO);
        MessageDTO toMessage = page.getContent().get(19);

        // the next scrolling request must return messages
        // oder then the oldest message from the initial history response
        page = restHelper.getMessagesBackwardScroll(chatDTO, toMessage);

        // there must be 20 messages older then the oldest from the initial request
        assertNotNull(page);
        assertEquals(20, page.getContent().size());

        Optional<MessageDTO> messageAfter = page.getContent().stream()
                .filter(m -> m.getTimestamp().isAfter(toMessage.getTimestamp()))
                .findFirst();

        // scrolling method should return only messages which were before,
        // never which were after
        assertFalse(messageAfter.isPresent());
    }

}
