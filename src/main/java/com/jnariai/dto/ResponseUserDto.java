package com.jnariai.dto;

import com.jnariai.shared.PassagerType;

public record ResponseUserDto(
        String id,
        String name,
        String email,
        PassagerType passagerType
) {
    public ResponseUserDto {
    }
}
