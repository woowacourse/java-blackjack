package bet;

import match.MatchResult;

public class BetResult {
    private final int amount;

    public BetResult(MatchResult matchResult, int amount) {
        this.amount = calculateBettingResult(matchResult, amount);
    }

    public int calculateBettingResult(MatchResult matchResult, int amount) {
        return matchResult.calculateBettingResult(amount);
    }

    public int getAmount() {
        return amount;
    }
}
