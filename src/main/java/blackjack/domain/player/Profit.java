package blackjack.domain.player;

import blackjack.domain.game.GameResult;

import java.util.Objects;

public class Profit {
    private final int value;

    public Profit(int betAmount, GameResult gameResult) {
        this.value = calculateProfit(betAmount, gameResult);
    }

    private Profit(int value) {
        this.value = value;
    }

    public Profit addProfit(Profit other) {
        return new Profit(this.value + other.value);
    }

    public Profit negate() {
        return new Profit(-this.value);
    }

    public int getValue() {
        return value;
    }

    private int calculateProfit(int betAmount, GameResult gameResult) {
        if (gameResult == GameResult.LOSE) {
            return -1 * betAmount;
        }
        if (gameResult == GameResult.BLACKJACK) {
            return (int) (betAmount * 1.5);
        }
        if (gameResult == GameResult.WIN) {
            return betAmount;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
        return value == profit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
