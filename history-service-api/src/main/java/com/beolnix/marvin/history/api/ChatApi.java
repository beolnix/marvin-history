package com.beolnix.marvin.history.api;

import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;
import io.swagger.annotations.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "chats", description = "Chats API")
@FeignClient(Constants.FEIGN_CLIENT_NAME)
@RequestMapping(Constants.HISTORY_URL_ROOT)
public interface ChatApi {
    @ApiOperation("Method returns list of all existing chats.")
    @RequestMapping(method = RequestMethod.GET, value = "/chats")
    List<ChatDTO> getAllChats();

    @ApiOperation(value = "Method returns chat by name.", response = ChatDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Chat not found") })
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Name of the chat", name = "name", dataType = "String", required = true, paramType = "path"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/chats/name/{name}")
    ChatDTO getChatByName(@PathVariable("name") String name);

    @ApiOperation(value = "Method returns chat by id.", response = ChatDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Chat not found") })
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Id of the chat", name = "id", dataType = "int", required = true, paramType = "path"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/chats/id/{id}")
    ChatDTO getChatById(@PathVariable("id") String id);

    @ApiOperation("Method creates new chat based on provided model.")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Bad request") })
    @RequestMapping(method = RequestMethod.POST, value = "/chats")
    ChatDTO createChat(@RequestBody CreateChatDTO createChatDTO);
}
