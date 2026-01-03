package com.callmextrm.INVENTORY.dto.UserDto;


import jakarta.validation.constraints.NotBlank;


public record RoleToUser(
        @NotBlank String username,
        @NotBlank String rolename) {

}
