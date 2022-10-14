package com.deloitte.mule.tranning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    public T data;
    public List<String> message;
    public Integer code;
    public String status;
}
