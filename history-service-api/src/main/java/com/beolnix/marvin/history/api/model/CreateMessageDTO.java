package com.beolnix.marvin.history.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by beolnix on 23/01/16.
 */

@ApiModel("DTO for history message creation request")
public class CreateMessageDTO {

    @ApiModelProperty(value = "Author of the message", required = true)
    private String author;

    @ApiModelProperty(value = "Text of the message", required = true)
    private String msg;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
