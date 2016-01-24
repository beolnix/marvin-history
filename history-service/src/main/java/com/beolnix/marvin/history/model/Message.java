package com.beolnix.marvin.history.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by beolnix on 18/01/16.
 */

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

    public Message(Long id, Long chatId, String from, Date timestamp, String msg) {
        this.id = id;
        this.chatId = chatId;
        this.from = from;
        this.timestamp = timestamp;
        this.msg = msg;
    }
}
