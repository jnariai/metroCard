package com.jnariai.travel.pojo;

import com.jnariai.shared.Station;

import java.math.BigDecimal;

public record CollectionSummary(Station station, Long totalCost, BigDecimal totalFee, Long discountQuantity) {
}
