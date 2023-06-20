package com.jnariai.travel;

import com.jnariai.shared.Station;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record CreatedTravelDTO(String id, int cost, boolean hasDiscount, BigDecimal fee, Station station,
                               Timestamp boughtAt) {
}
