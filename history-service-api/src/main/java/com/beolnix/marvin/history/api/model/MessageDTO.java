package com.beolnix.marvin.history.api.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by beolnix on 23/01/16.
 */

@Data
public class MessageDTO {
    private Long id;
    private Long chatId;
    private String from;
    private Date timestamp;
    private String msg;
}
