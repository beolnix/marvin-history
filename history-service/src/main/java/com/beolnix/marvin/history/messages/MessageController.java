package com.beolnix.marvin.history.messages;

import com.beolnix.marvin.history.api.MessageApi;
import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by beolnix on 23/01/16.
 */

@RestController
public class MessageController implements MessageApi {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public Page<MessageDTO> getMessages(@RequestParam(value = "chatId", required = true) Long chatId,

                                        @RequestParam(value = "toMessageId", required = false) Long toMessageId,

                                        @RequestParam(value = "fromDateTime", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                        LocalDateTime fromDateTime,

                                        @RequestParam(value = "toDateTime", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                        LocalDateTime toDateTime,

                                        Pageable pageable) {
        return messageService.getMessages(chatId, toMessageId, fromDateTime, toDateTime, pageable);
    }

    @Override
    public MessageDTO createMessate(@RequestBody CreateMessageDTO createMessageDTO) {
        return messageService.createMessate(createMessageDTO);
    }
}
