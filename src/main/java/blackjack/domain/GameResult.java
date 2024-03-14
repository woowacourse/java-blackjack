package blackjack.domain;

import blackjack.strategy.profit.*;

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

    public ProfitStrategy getProfitStrategy() {
        return profitStrategy;
    }
}
