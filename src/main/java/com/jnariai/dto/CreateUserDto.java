package com.jnariai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
  @NotBlank String name,
  @NotBlank @Email String email,
  @NotBlank String password,
  @NotBlank String passagerType
) {
  
}
