package blackjack.domain;

import java.util.function.Function;

public enum BettingResult {
    BLACKJACK(betAmount -> (int) (betAmount.money() * 1.5)),
    WIN(BetAmount::money),
    DRAW(betAmount -> 0),
    LOSE(betAmount -> -betAmount.money());

    private final Function<BetAmount, Integer> profitCalculator;

    BettingResult(Function<BetAmount, Integer> profitCalculator) {
        this.profitCalculator = profitCalculator;
    }

    public static BettingResult of(MatchResult matchResult, Player player) {
        if (matchResult == MatchResult.WIN && player.isBlackjack()) {
            return BLACKJACK;
        }
        if (matchResult == MatchResult.WIN) {
            return WIN;
        }
        if (matchResult == MatchResult.DRAW) {
            return DRAW;
        }
        return LOSE;
    }

    public int calculateProfit(BetAmount betAmount) {
        return profitCalculator.apply(betAmount);
    }
}