package com.beolnix.marvin;

import com.beolnix.marvin.history.Application;
import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.api.model.NoteCreationDTO;
import com.beolnix.marvin.history.api.model.NoteDTO;
import com.beolnix.marvin.history.chats.domain.dao.ChatDAO;
import com.beolnix.marvin.history.messages.domain.dao.MessageDAO;
import com.beolnix.marvin.utils.RestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;

/**
 * Created by beolnix on 19/03/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest(randomPort = true)
@ActiveProfiles("integration")
public class NoteControllerIntegrationTest {
    @Value("${local.server.port}")
    private Integer port;

    @Autowired
    private ChatDAO chatDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private ObjectMapper objectMapper;

    private RestHelper restHelper;
    private String baseUrl;

    @Before
    public void before() {
        chatDAO.deleteAll();
        messageDAO.deleteAll();

        restHelper = new RestHelper(chatDAO, messageDAO, port);

        baseUrl = "http://localhost:"+port+"/api/v1/";

    }

    @Test
    public void testPublish() throws Exception {
        RestTemplate restTemplate = restHelper.getRestTemplate();

        ResponseEntity<NoteDTO> result = restTemplate.postForEntity(baseUrl + "/notes", noteDto(), NoteDTO.class);
        assertNotNull(result);
    }

    /*
     * Test verifies that service accept json with unkown fields
     */
    @Test
    public void testPublishIncorrectJson() throws Exception {
        URL url = Resources.getResource("incorrectJson.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        RestTemplate restTemplate = restHelper.getRestTemplate();

        ResponseEntity<NoteDTO> result = restTemplate.postForEntity(baseUrl + "/notes", json, NoteDTO.class);
        assertNotNull(result);
    }

    public NoteCreationDTO noteDto() throws Exception {
        NoteCreationDTO dto = new NoteCreationDTO();
        dto.setTopic("test");
        dto.setQuestion(Collections.singletonList(msgDto()));
        dto.setAnswer(Collections.singletonList(msgDto()));
        return dto;
    }

    public MessageDTO msgDto() {
        MessageDTO dto = new MessageDTO();
        dto.setAuthor("test Autor");
        dto.setChatId("123");
        dto.setId("3314");
        dto.setMsg("test message");
        dto.setTimestamp(LocalDateTime.now());
        return dto;
    }
}
