package com.beolnix.marvin.history.api;

import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by beolnix on 23/01/16.
 */
@FeignClient("history")
public interface MessageApi {

    @RequestMapping(method = RequestMethod.GET, value = "/messages")
    List<MessageDTO> getMessages(@RequestParam(value = "chatId", required = true) Long chatId,
                                 @RequestParam(value = "toMessageId", required = false) Long fromMessageId,
                                 @RequestParam(value = "fromDateTime", required = false) String fromDateTime,
                                 @RequestParam(value = "toDateTime", required = false) String toDateTime);

    @RequestMapping(method = RequestMethod.POST, value = "/messages")
    void createMessate(CreateMessageDTO createMessageDTO);
}
