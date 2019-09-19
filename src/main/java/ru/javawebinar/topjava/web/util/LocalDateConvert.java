package ru.javawebinar.topjava.web.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LocalDateConvert implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        try{
            return LocalDate.parse(source);
        } catch (Exception e){
            return null;
        }
    }
}
