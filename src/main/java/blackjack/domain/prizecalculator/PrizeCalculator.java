package blackjack.domain.prizecalculator;

public interface PrizeCalculator {

    double calculate(final int playerScore, final int dealerScore, final boolean dealerBlackjack,
                     final double bettingAmount);
}
