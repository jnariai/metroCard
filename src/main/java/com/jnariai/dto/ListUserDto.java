package com.jnariai.dto;

import com.jnariai.shared.PassagerType;

public record ListUserDto(
    String id,
    String name,
    String email,
    PassagerType passagerType) {

}
