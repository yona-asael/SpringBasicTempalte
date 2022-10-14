package com.deloitte.mule.tranning.converters;

import com.deloitte.mule.tranning.enums.Status;
import org.springframework.core.convert.converter.Converter;


public class StatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        try {
            return Status.forValue(Integer.valueOf(source));
        } catch(Exception e) {
            return null;
        }
    }
}
