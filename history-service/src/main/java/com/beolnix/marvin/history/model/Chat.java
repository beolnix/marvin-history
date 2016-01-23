package com.beolnix.marvin.history.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by beolnix on 18/01/16.
 */

@Data
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

}
