package com.deloitte.mule.tranning.dto;

import com.deloitte.mule.tranning.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDTO {
    @NotNull
    private String name;

    @NotNull
    private String code;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantity;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("arrive_date")
    private Date arriveDate;

    private Status status;
}
