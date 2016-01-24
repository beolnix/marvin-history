package com.beolnix.marvin.history.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by beolnix on 23/01/16.
 */

@ApiModel("Chat message DTO")
public class MessageDTO {

    @ApiModelProperty("Message id")
    private Long id;

    @ApiModelProperty("Id of the Chat")
    private Long chatId;

    @ApiModelProperty("Message autor")
    private String autor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty("Date and Time of the message")
    private LocalDateTime timestamp;

    @ApiModelProperty("Text of the message")
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
