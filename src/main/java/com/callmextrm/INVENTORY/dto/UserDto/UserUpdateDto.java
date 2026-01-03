package com.callmextrm.INVENTORY.dto.UserDto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDto(
        @NotBlank String username
) {
}
