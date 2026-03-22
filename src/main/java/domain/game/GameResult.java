package domain.game;

import java.math.BigDecimal;

public enum GameResult {
    WIN("승", new BigDecimal("1")),
    LOSE("패", new BigDecimal("-1")),
    DRAW("무", BigDecimal.ZERO),
    BLACKJACK("블랙잭", new BigDecimal("1.5"));

    private final String resultKoreanName;
    private final BigDecimal profitRate;

    GameResult(String resultKoreanName, BigDecimal profitRate) {
        this.resultKoreanName = resultKoreanName;
        this.profitRate = profitRate;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }
}
