package com.beolnix.marvin.history.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by beolnix on 21/02/16.
 */
@ApiModel("Note DTO")
public class NoteDTO {

    @ApiModelProperty("Note id")
    private String id;

    @ApiModelProperty("Note topic")
    private String topic;

    @ApiModelProperty("Note question")
    private List<MessageDTO> question;

    @ApiModelProperty("Note answer")
    private List<MessageDTO> answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<MessageDTO> getQuestion() {
        return question;
    }

    public void setQuestion(List<MessageDTO> question) {
        this.question = question;
    }

    public List<MessageDTO> getAnswer() {
        return answer;
    }

    public void setAnswer(List<MessageDTO> answer) {
        this.answer = answer;
    }
}
