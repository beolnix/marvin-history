package com.beolnix.marvin.history.api;

import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.api.model.NoteCreationDTO;
import com.beolnix.marvin.history.api.model.NoteDTO;
import io.swagger.annotations.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by beolnix on 21/02/16.
 */

@Api(value = "messages", description = "Chat Messages API")
@FeignClient(Constants.FEIGN_CLIENT_NAME)
@RequestMapping(Constants.HISTORY_URL_ROOT)
public interface NotesApi {
    @ApiOperation(value = "Method creates new note", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Note to be created", name="noteCreationDTO", required = true, dataType = "NoteCreationDTO", paramType = "body"),
    })
    @ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(method = RequestMethod.POST, value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    NoteDTO createNote(@RequestBody NoteCreationDTO noteCreationDTO);
}
