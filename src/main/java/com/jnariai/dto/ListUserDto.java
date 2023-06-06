package com.jnariai.dto;

import com.jnariai.shared.PassangerType;

public record ListUserDto(
    String id,
    String name,
    String email,
    PassangerType passangerType) {

}
