package blackjack.model.game;

import blackjack.model.participant.Bet;
import java.util.function.BiFunction;

public enum BlackjackResult {
    WIN(((bet, earningRate) -> bet.amount() * earningRate)),
    LOSE((bet, earningRate) -> bet.amount() * earningRate * -1),
    PUSH((bet, earningRate) -> 0.0);

    private final BiFunction<Bet, Double, Double> profitCalculator;

    BlackjackResult(BiFunction<Bet, Double, Double> profitCalculator) {
        this.profitCalculator = profitCalculator;
    }

    public double calculateProfit(Bet bet, double earningRate) {
        return profitCalculator.apply(bet, earningRate);
    }
}
