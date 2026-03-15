package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.function.IntUnaryOperator;

public enum Result {
    BLACKJACK(bet -> bet + bet / 2),
    WIN(bet -> bet),
    DRAW(bet -> 0),
    LOSE(bet -> bet * -1);

    private final IntUnaryOperator profitCalculationOperator;

    Result(IntUnaryOperator profitCalculationOperator) {
        this.profitCalculationOperator = profitCalculationOperator;
    }

    public static Result of(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }

        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        return compareScore(dealer.getScore(), player.getScore());
    }

    public int calculateProfit(int betAmount) {
        return profitCalculationOperator.applyAsInt(betAmount);
    }

    private static Result compareScore(int dealerScore, int playerScore) {
        if (dealerScore < playerScore) {
            return WIN;
        }

        if (dealerScore > playerScore) {
            return LOSE;
        }

        return DRAW;
    }
}
