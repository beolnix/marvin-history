package com.beolnix.marvin.history.api;

import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import io.swagger.annotations.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Api(value = "messages", description = "Chat Messages API")
@FeignClient(Constants.FEIGN_CLIENT_NAME)
@RequestMapping(Constants.HISTORY_URL_ROOT)
public interface MessageApi {

    @ApiOperation(value = "Method returns all messages within the given filtration params in ordered by timestame DESC", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "size of the page", name = "size", defaultValue = "10", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(value = "number of the page", name = "page", defaultValue = "0", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(value = "Id of the chat", name="chatId", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(value = "Method will filter out messages with id greater then given",
                    name="toMessageId", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(value = "Method will filter out messages older then given date. Format=yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                    name="fromDateTime", example = "2000-10-31 01:30:00.000-05:00", dataType = "String", required = false, paramType = "query"),
            @ApiImplicitParam(value = "Method will filter out messages newer then given date. Format=yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                    name="toDateTime", example = "2000-10-31 01:30:00.000-05:00", required = false, paramType = "query")
    })
    @ApiParam(value = "Message id limit. Method will return messages with id less then given",
            name="toMessageId",
            required = false)
    @RequestMapping(method = RequestMethod.GET, value = "/chats/{chatId}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<MessageDTO> getMessages(@PathVariable(value = "chatId") String chatId,
                                 @RequestParam(value = "toMessageId", required = false) String toMessageId,

                                 @RequestParam(value = "fromDateTime", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                 LocalDateTime fromDateTime,

                                 @RequestParam(value = "toDateTime", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                 LocalDateTime toDateTime,

                                 Pageable pageable);



    @ApiOperation(value = "Method creates new message based on provided model", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Id of the chat", name="chatId", required = true, dataType = "string", paramType = "path"),
    })
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Chat not found") })
    @RequestMapping(method = RequestMethod.POST, value = "/chats/{chatId}/messages", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MessageDTO createMessate(@PathVariable("chatId") String chatId,
                             @RequestBody CreateMessageDTO createMessageDTO);
}
