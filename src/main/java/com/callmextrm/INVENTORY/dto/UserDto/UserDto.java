package com.callmextrm.INVENTORY.dto.UserDto;

import java.util.Set;

public record UserDto(
        Long id,
        String username,
        Set<String> roles

                      ) {
}
