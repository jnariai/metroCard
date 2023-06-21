package com.jnariai.travel.dto;

import com.jnariai.shared.Station;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record CreatedTravelDTO(String id, int cost, boolean hasDiscount, BigDecimal fee, Station station,
                               Timestamp boughtAt) {
}
