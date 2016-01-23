package com.beolnix.marvin.history.api.model;

import lombok.Data;

/**
 * Created by beolnix on 23/01/16.
 */

@Data
public class ChatDTO {
    private Long id;
    private String name;
    private String protocol;
    private Boolean isConference;
}
