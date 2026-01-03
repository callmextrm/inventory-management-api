package com.callmextrm.INVENTORY.dto.ProductDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductCreateDto(
        @NotBlank String name,
        @NotNull @PositiveOrZero Integer quantity,
        @NotNull BigDecimal price

        ) {
}
