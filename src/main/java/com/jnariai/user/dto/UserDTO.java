package com.jnariai.user.dto;

import com.jnariai.shared.PassengerType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
    @NotBlank String id,
    @NotBlank String name,
    @Email String email,
    @NotBlank PassengerType passengerType) {
}
