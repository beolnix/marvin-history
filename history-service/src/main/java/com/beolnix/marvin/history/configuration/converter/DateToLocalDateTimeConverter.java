package com.beolnix.marvin.history.configuration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by beolnix on 13/02/16.
 */
@Component
public class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

    @Override
    public LocalDateTime convert(Date source) {
        return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }
}
