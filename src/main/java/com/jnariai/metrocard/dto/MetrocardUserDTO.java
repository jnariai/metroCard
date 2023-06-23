package com.jnariai.metrocard.dto;

import java.math.BigDecimal;

public record MetrocardUserDTO(String id, BigDecimal balance, boolean autoRecharge, boolean active) {
}
