package domain;

import java.math.BigDecimal;

public enum GameResultStatus {
    BLACKJACK(BigDecimal.valueOf(1.5)),
    WIN(BigDecimal.valueOf(1)),
    DRAW(BigDecimal.valueOf(0)),
    LOSE(BigDecimal.valueOf(-1));

    private final BigDecimal payout;

    GameResultStatus(BigDecimal payout) {
        this.payout = payout;
    }

    public BigDecimal calculateProfit(int bettingMoney) {
        return payout.multiply(BigDecimal.valueOf(bettingMoney));
    }
}
