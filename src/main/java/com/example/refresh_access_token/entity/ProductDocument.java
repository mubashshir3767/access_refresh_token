package com.example.refresh_access_token.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class ProductDocument extends BaseDocument{
    private String  name;
    private int quantity;
}
