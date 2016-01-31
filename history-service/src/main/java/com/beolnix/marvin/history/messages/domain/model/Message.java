package com.beolnix.marvin.history.messages.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String autor;

    @Column
    private LocalDateTime timestamp;

    @Column
    private String msg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
