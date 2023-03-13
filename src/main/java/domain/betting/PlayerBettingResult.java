package domain.betting;

import java.util.function.DoubleUnaryOperator;

public enum PlayerBettingResult {
    WIN(bettingMoney -> bettingMoney),
    LOSE(bettingMoney -> bettingMoney * -1),
    BLACKJACK(bettingMoney -> bettingMoney * 1.5);

    private final DoubleUnaryOperator doubleUnaryOperator;

    PlayerBettingResult(DoubleUnaryOperator doubleUnaryOperator) {
        this.doubleUnaryOperator = doubleUnaryOperator;
    }

    public double calculateFinalProfit(int bettingMoney) {
        return doubleUnaryOperator.applyAsDouble(bettingMoney);
    }
}
