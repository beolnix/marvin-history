package com.beolnix.marvin.history.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


/**
 * Created by beolnix on 18/01/16.
 */

@Configuration
public class HistoryConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void customJacksonObjectMapper(){
        Jdk8Module jdk8Module = new Jdk8Module();
        objectMapper.registerModule(new Jdk8Module());
    }

}
