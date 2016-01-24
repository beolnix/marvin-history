package com.beolnix.marvin.history.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by beolnix on 23/01/16.
 */

@ApiModel("Chat model")
public class ChatDTO {

    @ApiModelProperty("Chat id")
    private Long id;

    @ApiModelProperty("Name of the chat")
    private String name;

    @ApiModelProperty("Chat protocol")
    private String protocol;

    @ApiModelProperty("Conference flag")
    private Boolean conference;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.conference = conference;
    }

    public Boolean isConference() {
        return this.conference;
    }
}
