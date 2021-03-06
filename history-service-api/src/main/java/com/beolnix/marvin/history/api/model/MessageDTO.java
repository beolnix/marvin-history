package com.beolnix.marvin.history.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * Created by beolnix on 23/01/16.
 */

@ApiModel("Chat message DTO")
public class MessageDTO {

    @ApiModelProperty("Message id")
    private String id;

    @ApiModelProperty("Id of the Chat")
    private String chatId;

    @ApiModelProperty("Message author")
    private String author;

    @ApiModelProperty(value = "Date and Time of the message", dataType = "LocalDateTime", example = "2016-02-15T07:42:11.626")
    private LocalDateTime timestamp;

    @ApiModelProperty("Text of the message")
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
