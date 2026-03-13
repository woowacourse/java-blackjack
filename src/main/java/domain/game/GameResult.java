package domain.game;

import java.math.BigDecimal;

public enum GameResult {

    WIN("승", BigDecimal.valueOf(1.0)),
    PUSH("무", BigDecimal.valueOf(0.0)),
    DEFEAT("패", BigDecimal.valueOf(-1.0)),
    BLACKJACK_WIN("블랙잭승", BigDecimal.valueOf(1.5));

    private final String info;
    private final BigDecimal yield;

    GameResult(String info, BigDecimal yield) {
        this.info = info;
        this.yield = yield;
    }

    public String getInfo() {
        return info;
    }

    public BigDecimal getYield() {
        return yield;
    }
}
