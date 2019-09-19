package ru.javawebinar.topjava.web.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class LocalTimeConvert implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        try{
            return LocalTime.parse(source);
        } catch (Exception e){
            return null;
        }
    }
}
