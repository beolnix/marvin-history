package com.beolnix.marvin.history.notes.domain.model;

import com.beolnix.marvin.history.messages.domain.model.Message;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by beolnix on 21/02/16.
 */
public class Note {

    @Id
    private String id;

    private String topic;
    private List<Message> question;
    private List<Message> answer;

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

    public List<Message> getQuestion() {
        return question;
    }

    public void setQuestion(List<Message> question) {
        this.question = question;
    }

    public List<Message> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Message> answer) {
        this.answer = answer;
    }
}
