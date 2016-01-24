package com.beolnix.marvin.history.api.model;


/**
 * Created by beolnix on 23/01/16.
 */

public class CreateChatDTO {

    private String name;

    private String protocol;

    private Boolean isConference;

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
        return isConference;
    }

    public void setConference(Boolean conference) {
        isConference = conference;
    }
}
