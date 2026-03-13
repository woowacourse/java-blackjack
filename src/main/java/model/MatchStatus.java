package model;

import java.math.BigDecimal;

public enum MatchStatus {
    BLACKJACK("블랙잭 승", new BigDecimal("1.5")),
    WIN("승", BigDecimal.ONE),
    DRAW("무", BigDecimal.ZERO),
    LOSE("패", BigDecimal.ONE.negate());

    private final String status;
    private final BigDecimal multiplier;

    MatchStatus(String status, BigDecimal multiplier) {
        this.status = status;
        this.multiplier = multiplier;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

}
