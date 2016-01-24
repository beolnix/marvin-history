package com.beolnix.marvin.history.api.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by beolnix on 23/01/16.
 */

@ApiModel("DTO for Chat history creation request")
public class CreateChatDTO {

    @ApiModelProperty(value = "Name of the Chat", required = true)
    private String name;

    @ApiModelProperty(value = "Chat protocol", required = true)
    private String protocol;

    @ApiModelProperty(value = "Conference flag", required = true)
    private Boolean conference;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Boolean getConference() {
        return conference;
    }

    public void setConference(Boolean conference) {
        conference = conference;
    }

    public Boolean isConference() {
        return conference;
    }
}
