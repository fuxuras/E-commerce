package com.enoca.ecommorce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockAmount;
}
