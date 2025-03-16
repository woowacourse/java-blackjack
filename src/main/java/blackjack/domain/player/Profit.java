package blackjack.domain.player;

import blackjack.domain.game.GameResult;

public record Profit(int value) {

    public Profit addProfit(Profit other) {
        return new Profit(this.value + other.value);
    }

    public Profit negate() {
        return new Profit(-this.value);
    }

    public static Profit calculateFrom(int betAmount, GameResult gameResult) {
        if (gameResult == GameResult.LOSE) {
            return new Profit(-1 * betAmount);
        }
        if (gameResult == GameResult.BLACKJACK) {
            return new Profit((int) (betAmount * 1.5));
        }
        if (gameResult == GameResult.WIN) {
            return new Profit(betAmount);
        }
        return new Profit(0);
    }
}
