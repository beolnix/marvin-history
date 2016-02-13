package com.beolnix.marvin.history.chats.domain.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by beolnix on 18/01/16.
 */

@Document
public class Chat {

    @Id
    private String id;

    @Indexed
    private String name;
    private String protocol;
    private Boolean isConference;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
