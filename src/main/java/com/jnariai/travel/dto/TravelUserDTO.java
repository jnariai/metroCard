package com.jnariai.travel.dto;

import com.jnariai.shared.Station;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TravelUserDTO(int cost, boolean hasDiscount, BigDecimal fee, Station station, Timestamp boughtAt,
                            String metrocardId) {
}
