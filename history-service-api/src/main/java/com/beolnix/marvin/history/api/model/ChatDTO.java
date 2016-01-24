package com.beolnix.marvin.history.api.model;

/**
 * Created by beolnix on 23/01/16.
 */

public class ChatDTO {
    private Long id;
    private String name;
    private String protocol;
    private Boolean isConference;

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
        return isConference;
    }

    public void setConference(Boolean conference) {
        isConference = conference;
    }
}
