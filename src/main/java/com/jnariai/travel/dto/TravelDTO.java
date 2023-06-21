package com.jnariai.travel.dto;

import com.jnariai.shared.Station;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TravelDTO(@NotBlank String metrocardId, @NotNull Station station) {
}