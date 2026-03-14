package blackjack.model.game;

import blackjack.model.state.BlackjackState;
import blackjack.model.state.Bust;
import blackjack.model.participant.Bet;
import java.util.function.BiFunction;

public enum BlackjackResult {
    WIN(((bet, earningRate) -> bet.amount() * earningRate)),
    LOSS((bet, earningRate) -> bet.amount() * earningRate * -1),
    PUSH((bet, earningRate) -> 0.0);

    private final BiFunction<Bet, Double, Double> profitCalculator;

    BlackjackResult(BiFunction<Bet, Double, Double> profitCalculator) {
        this.profitCalculator = profitCalculator;
    }

    public static BlackjackResult judge(BlackjackState playerState, BlackjackState dealerState) {
        if (playerState instanceof Bust) {
            return BlackjackResult.LOSS;
        }
        if (dealerState instanceof Bust) {
            return BlackjackResult.WIN;
        }

        int playerScore = playerState.getScore();
        int dealerScore = dealerState.getScore();
        if (playerScore > dealerScore) {
            return BlackjackResult.WIN;
        }
        if (playerScore == dealerScore) {
            return BlackjackResult.PUSH;
        }

        return BlackjackResult.LOSS;
    }

    public double calculateProfit(Bet bet, double earningRate) {
        return profitCalculator.apply(bet, earningRate);
    }
}
