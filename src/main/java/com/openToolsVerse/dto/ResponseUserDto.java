package com.template.template.dto;

import com.template.template.shared.PassagerType;

public record ResponseUserDto(
        String id,
        String name,
        String email,
        PassagerType passagerType
) {
    public ResponseUserDto {
    }
}
