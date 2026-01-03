package com.callmextrm.INVENTORY.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public record QuantityDTO(
        @NotNull @PositiveOrZero Integer quantity
        ) {
}