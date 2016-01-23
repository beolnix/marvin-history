package com.beolnix.marvin.history.controller;

import com.beolnix.marvin.history.api.MessageApi;
import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by beolnix on 23/01/16.
 */
public class MessageController  implements MessageApi {
    @Override
    public List<MessageDTO> getMessages(@RequestParam(value = "chatId", required = true) Long chatId, @RequestParam(value = "toMessageId", required = false) Long fromMessageId, @RequestParam(value = "fromDateTime", required = false) String fromDateTime, @RequestParam(value = "toDateTime", required = false) String toDateTime) {
        return null;
    }

    @Override
    public void createMessate(CreateMessageDTO createMessageDTO) {

    }
}
