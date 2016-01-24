package com.beolnix.marvin.history.controller;

import com.beolnix.marvin.history.api.MessageApi;
import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by beolnix on 23/01/16.
 */

@RestController
public class MessageController  implements MessageApi {
    @Override
    public List<MessageDTO> getMessages(Long chatId,
                                        Long fromMessageId,
                                        String fromDateTime,
                                        String toDateTime) {
        return null;
    }

    @Override
    public void createMessate(CreateMessageDTO createMessageDTO) {

    }
}
