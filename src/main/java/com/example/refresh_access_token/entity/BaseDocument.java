package com.example.refresh_access_token.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseDocument {
    @CreatedDate
    private Timestamp createdDate;

    @LastModifiedDate
    private Timestamp updateDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updateBy;
}
