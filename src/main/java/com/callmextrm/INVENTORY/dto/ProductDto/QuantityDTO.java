package com.callmextrm.INVENTORY.dto.ProductDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public record QuantityDTO(
        @NotNull @PositiveOrZero Integer quantity
        ) {
}