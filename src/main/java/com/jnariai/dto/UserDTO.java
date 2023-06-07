package com.jnariai.dto;

import com.jnariai.shared.PassangerType;

public record UserDTO(
    String id,
    String name,
    String email,
    PassangerType passangerType) {

}
