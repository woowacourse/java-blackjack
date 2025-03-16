package blackjack.domain.game;

import blackjack.domain.betting.BettingAmount;

public enum WinningType {
    BLACKJACK_WIN("블랙잭 승", 2.5),
    WIN("승", 2.0),
    DEFEAT("패", 0),
    DRAW("무", 1.0);

    private final String displayName;
    private final double winningRate;

    WinningType(final String displayName, final double winningRate) {
        this.displayName = displayName;
        this.winningRate = winningRate;
    }

    public int calculateWinningAmount(final BettingAmount amount) {
        return amount.multiply(winningRate);
    }
}
