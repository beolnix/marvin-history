package com.beolnix.marvin.history.model;

import javax.persistence.*;

/**
 * Created by beolnix on 18/01/16.
 */

@Entity
@Table(name="mh_chat")
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String protocol;

    @Column(name="IS_CONFERENCE")
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
