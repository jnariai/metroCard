package com.jnariai.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserPasswordDTO(@NotBlank String oldPassword, @NotBlank String newPassword) {
}
