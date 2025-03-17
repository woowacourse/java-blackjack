package bet;

import result.MatchResult;

public class BetResult {
    private final int amount;

    public BetResult(MatchResult matchResult, int amount) {
        this.amount = calculateBettingResult(matchResult, amount);
    }

    public int calculateBettingResult(MatchResult matchResult, int amount) {
        if (matchResult == MatchResult.WIN_WITH_BLACKJACK) {
            return (int) (amount * 1.5);
        }
        if (matchResult == MatchResult.LOSE) {
            return -amount;
        }
        if (matchResult == MatchResult.DRAW) {
            return amount - amount;
        }
        return amount;
    }

    public int getAmount() {
        return amount;
    }
}
