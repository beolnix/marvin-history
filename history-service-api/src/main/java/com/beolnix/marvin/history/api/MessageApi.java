package com.beolnix.marvin.history.api;

import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import io.swagger.annotations.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;


@Api(value = "messages", description = "Chat Messages API")
@FeignClient(Constants.FEIGN_CLIENT_NAME)
@RequestMapping(Constants.HISTORY_URL_ROOT)
public interface MessageApi {

    @ApiOperation("Method returns all messages within the given filtration params in ordered by timestame DESC")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "size of the page", name = "size", defaultValue = "10", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(value = "number of the page", name = "page", defaultValue = "0", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(value = "Id of the chat", name="chatId", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "Method will filter out messages with id greater then given",
                    name="toMessageId", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(value = "Method will filter out messages older then given date. Format=yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                    name="fromDateTime", example = "2000-10-31 01:30:00.000-05:00", dataType = "String", required = false, paramType = "query"),
            @ApiImplicitParam(value = "Method will filter out messages newer then given date. Format=yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                    name="toDateTime", example = "2000-10-31 01:30:00.000-05:00", required = false, paramType = "query")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/messages")

    @ApiParam(value = "Message id limit. Method will return messages with id less then given",
            name="toMessageId",
            required = false)
    Page<MessageDTO> getMessages(@RequestParam(value = "chatId", required = true) Long chatId,
                                 @RequestParam(value = "toMessageId", required = false) Long toMessageId,

                                 @RequestParam(value = "fromDateTime", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                 LocalDateTime fromDateTime,

                                 @RequestParam(value = "toDateTime", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                 LocalDateTime toDateTime,

                                 Pageable pageable);



    @ApiOperation("Method creates new message based on provided model")
    @RequestMapping(method = RequestMethod.POST, value = "/messages")
    MessageDTO createMessate(@RequestBody CreateMessageDTO createMessageDTO);
}
