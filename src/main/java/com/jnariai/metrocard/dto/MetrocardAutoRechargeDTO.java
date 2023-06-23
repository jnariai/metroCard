package com.jnariai.metrocard.dto;

import jakarta.validation.constraints.NotNull;

public record MetrocardAutoRechargeDTO(@NotNull boolean autoRecharge) {}
