package com.deloitte.mule.tranning.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updatedAt;

    @JsonIgnore
    @Column( length = 10, nullable = false)
    private String operation;

    @PrePersist
    public void onPrePersist() {
        setOperation("INSERT");
    }

    @PreUpdate
    public void onPreUpdate() {
        setOperation("UPDATE");
    }

    @PreRemove
    public void onPreRemove() {
        setOperation("DELETE");
    }


}
