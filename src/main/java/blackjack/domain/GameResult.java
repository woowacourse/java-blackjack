package blackjack.domain;

import blackjack.strategy.profit.*;

import java.math.BigDecimal;

public enum GameResult {

    BLACKJACK(new BlackjackWinStrategy()),
    WIN(new WinStrategy()),
    DRAW(new DrawStrategy()),
    LOSE(new LoseStrategy()),
    ;

    private final ProfitStrategy profitStrategy;

    GameResult(ProfitStrategy profitStrategy) {
        this.profitStrategy = profitStrategy;
    }

    public BigDecimal calculateProfit(BigDecimal betting) {
        return profitStrategy.calculate(betting);
    }
}
