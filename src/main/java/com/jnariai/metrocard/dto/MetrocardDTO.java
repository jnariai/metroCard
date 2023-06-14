package com.jnariai.metrocard.dto;

import java.math.BigDecimal;

public record MetrocardDTO(
  String id,
  BigDecimal balance,
  boolean autoRecharge,
  boolean active,
  String userId) {
}
