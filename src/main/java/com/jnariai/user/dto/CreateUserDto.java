package com.jnariai.user.dto;

import com.jnariai.shared.PassengerType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(@NotBlank String name, @NotBlank @Email String email, @NotBlank String password,
                            @NotNull PassengerType passengerType) {

}
