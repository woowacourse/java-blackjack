package model.game;

import java.math.BigDecimal;
import model.betting.Bet;

public enum GameResult {
    BLACKJACK_WIN(new BigDecimal("1.5")),
    WIN(new BigDecimal("1.0")),
    PUSH(new BigDecimal("1.0")),
    LOOSE(new BigDecimal("-1.0"));

    private final BigDecimal profitRate;

    GameResult(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal calculateProfit(Bet bet) {
        return bet.getAmount().multiply(profitRate);
    }
}
