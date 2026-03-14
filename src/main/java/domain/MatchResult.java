package domain;

import java.math.BigDecimal;

public enum MatchResult {
    BLACKJACK_WIN("1.5"),
    WIN("1.0"),
    DRAW("0.0"),
    LOSE("-1.0"),
    ;

    private final BigDecimal profitRate;

    MatchResult(String profitRate) {
        this.profitRate = new BigDecimal(profitRate);
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }
}
