package com.beolnix.marvin.history.configuration;

import com.beolnix.marvin.history.configuration.converter.DateToLocalDateTimeConverter;
import com.beolnix.marvin.history.configuration.converter.LocalDateTimeToDateConverter;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;


/**
 * Created by beolnix on 18/01/16.
 */

@Configuration
@EnableMongoRepositories("com.beolnix.marvin.history")
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

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(
                new DateToLocalDateTimeConverter(),
                new LocalDateTimeToDateConverter()
            )
        );
    }




}
