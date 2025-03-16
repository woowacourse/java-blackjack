package blackjack.domain.player;

import blackjack.domain.game.GameResult;

import java.util.Objects;

public final class Profit {
    private final int value;

    private Profit(int value) {
        this.value = value;
    }

    public Profit addProfit(Profit other) {
        return new Profit(this.value + other.value);
    }

    public Profit negate() {
        return new Profit(-this.value);
    }

    public int value() {
        return value;
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

    public static Profit from(int betAmount) {
        return new Profit(betAmount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Profit) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
