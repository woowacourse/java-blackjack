package domain.money;

import domain.card.MatchResult;

public class BettingResult {

    private final int earnings;

    private BettingResult(int earnings) {
        this.earnings = earnings;
    }

    public static BettingResult from(int betAmount, MatchResult matchResult, boolean isBlackJack) {
        if (matchResult == MatchResult.WIN && isBlackJack) {
            return new BettingResult((int) (betAmount * 1.5));
        }
        if (matchResult == MatchResult.WIN) {
            return new BettingResult(betAmount);
        }
        if (matchResult == MatchResult.LOSE) {
            return new BettingResult(-betAmount);
        }
        return new BettingResult(0);
    }

    public int getEarnings() {
        return earnings;
    }
}
