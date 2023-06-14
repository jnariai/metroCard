package com.jnariai.metrocard.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;

public record DepositMoneyMetrocardDTO(
  @Positive BigDecimal money
) {
  
}
