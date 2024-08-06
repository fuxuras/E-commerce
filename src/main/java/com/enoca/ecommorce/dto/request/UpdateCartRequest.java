package com.enoca.ecommorce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCartRequest {
    private Long cartId;
    private List<UpdateCartProductRequest> products;
}
