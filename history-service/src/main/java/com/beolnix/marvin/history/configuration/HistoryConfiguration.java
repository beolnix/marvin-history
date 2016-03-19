package com.beolnix.marvin.history.configuration;

import com.beolnix.marvin.history.configuration.converter.DateToLocalDateTimeConverter;
import com.beolnix.marvin.history.configuration.converter.LocalDateTimeToDateConverter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;


/**
 * Created by beolnix on 18/01/16.
 */

@Configuration
@ComponentScan("com.beolnix.marvin.history")
@EnableMongoRepositories("com.beolnix.marvin.history")
@EnableConfigurationProperties(SecurityConfig.class)
public class HistoryConfiguration extends AbstractMongoConfiguration {

    @Value("${history.mongo.host}")
    private String mongoHost;

    @Value("${history.mongo.database}")
    private String mongoDatabase;

    @Override
    protected String getDatabaseName() {
        return mongoDatabase;
    }

    @Override
    public MongoClient mongo() throws Exception {
        return new MongoClient(mongoHost);
    }

    @Bean
    public ObjectMapper jsonMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        //Fully qualified path shows I am using latest enum
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.
                WRITE_DATES_AS_TIMESTAMPS , false);
        objectMapper.getSerializationConfig().with(new ISO8601DateFormat());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(
                new DateToLocalDateTimeConverter(),
                new LocalDateTimeToDateConverter()
            )
        );
    }




}
