package com.beolnix.marvin.history.configuration.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by beolnix on 14/02/16.
 */
public class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {

    @Override
    public Date convert(LocalDateTime source) {
        return source == null ? null : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
    }
}

