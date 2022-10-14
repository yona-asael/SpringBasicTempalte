package com.deloitte.mule.tranning.models;


import com.deloitte.mule.tranning.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends  BaseEntity implements Serializable {

    @Column(length = 255,nullable = false,  name = "name")
    private String name;
    @Column(length = 255,nullable = false,  name = "slug")
    private String slug;
    @Column(length = 15,nullable = false,  name = "code")
    private String code;

    @Column(scale = 2,nullable = false,  name = "price")
    private BigDecimal price;

    @Column(nullable = false,  name = "quantity")
    private Integer quantity;

    @JsonProperty("arrive_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(nullable = false,  name = "arrive_date")
    private Date arriveDate;

    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    public Product(String name, String slug, String code, BigDecimal price, Integer quantity, Date arriveDate, Status status) {
        super();
        this.name = name;
        this.slug = slug;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.arriveDate = arriveDate;
        this.status = status;
    }
}
