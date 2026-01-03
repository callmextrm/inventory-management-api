package com.callmextrm.INVENTORY.dto.UserDto;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDto(
        @NotBlank String username,
        @NotBlank String password
) {
}
