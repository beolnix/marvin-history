package com.beolnix.marvin.history.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by beolnix on 23/01/16.
 */

@ApiModel("DTO for history message creation request")
public class CreateMessageDTO {

    @ApiModelProperty(value = "Id of the chat", required = true)
    private String chatId;

    @ApiModelProperty(value = "Autor of the message", required = true)
    private String autor;

    @ApiModelProperty(value = "Text of the message", required = true)
    private String msg;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
