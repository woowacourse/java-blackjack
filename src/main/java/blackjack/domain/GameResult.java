package blackjack.domain;

import java.math.BigDecimal;

public enum GameResult {
    WIN("승", new BigDecimal("1")),
    TIE("무", new BigDecimal("0")),
    LOSE("패", new BigDecimal("-1")),
    BLACKJACK_WIN("블랙잭승", new BigDecimal("1.5"));

    private final String name;
    private final BigDecimal earningRate;

    GameResult(String name, BigDecimal earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getEarningRate() {
        return earningRate;
    }
}
