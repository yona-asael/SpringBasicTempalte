package com.deloitte.mule.tranning.services;

import com.deloitte.mule.tranning.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T>  ResponseDTO<T> createResponse(T data, List<String> messages, HttpStatus status) {
        return new ResponseDTO<T>(
                data,
                messages,
                status.value(),
                status.getReasonPhrase()
        );
    }
}
