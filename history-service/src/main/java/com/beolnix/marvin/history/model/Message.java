package com.beolnix.marvin.history.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by beolnix on 18/01/16.
 */

@Data
@Entity
@Table(name="mh_message")
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="chat_id")
    private Long chatId;

    @Column
    private String from;

    @Column
    private Date timestamp;

    @Column
    private String msg;

}
