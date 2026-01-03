package com.callmextrm.INVENTORY.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Pattern(
            regexp = "^[a-zA-Z0-9]+$",
            message = "Role must contain only letters and numbers"
    )
    private String rolename;






}
