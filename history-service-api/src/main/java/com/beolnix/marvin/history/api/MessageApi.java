package com.beolnix.marvin.history.api;

import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Api(value = "messages", description = "Chat Messages API")
@FeignClient(Constants.FEIGN_CLIENT_NAME)
@RequestMapping(Constants.HISTORY_URL_ROOT)
public interface MessageApi {

    @ApiOperation("Method returns all messages within the given filtration params in ordered by timestame DESC")
    @RequestMapping(method = RequestMethod.GET, value = "/messages")
    List<MessageDTO> getMessages(@RequestParam(value = "chatId", required = true) Long chatId,
                                 @RequestParam(value = "toMessageId", required = false) Long fromMessageId,
                                 @RequestParam(value = "fromDateTime", required = false) String fromDateTime,
                                 @RequestParam(value = "toDateTime", required = false) String toDateTime);

    @ApiOperation("Method creates new message based on provided model")
    @RequestMapping(method = RequestMethod.POST, value = "/messages")
    void createMessate(CreateMessageDTO createMessageDTO);
}
