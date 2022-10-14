package com.deloitte.mule.tranning.dto;

import com.deloitte.mule.tranning.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProductQueryDTO {
    private String name;
    private String code;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("arrive_date")
    private Date arriveDate;
    private Status status;
}
