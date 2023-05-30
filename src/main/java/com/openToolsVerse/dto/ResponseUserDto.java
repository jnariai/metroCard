package com.openToolsVerse.dto;

import com.openToolsVerse.shared.PassagerType;

public record ResponseUserDto(
        String id,
        String name,
        String email,
        PassagerType passagerType
) {
    public ResponseUserDto {
    }
}
