package domain;

import java.math.BigDecimal;

public enum MatchResult {
    BLACKJACK("블랙잭", new BigDecimal("1.5")),
    WIN("승", new BigDecimal("1.0")),
    LOSE("패", new BigDecimal("-1.0")),
    DRAW("무", new BigDecimal("0.0"));

    private final String korean;
    private final BigDecimal multiplier;

    MatchResult(String korean, BigDecimal multiplier) {
        this.korean = korean;
        this.multiplier = multiplier;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }
}
