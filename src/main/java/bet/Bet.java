package bet;

import result.MatchResult;

public class Bet {
    private int amount;

    public Bet(int amount) {
        validateBetAmount(amount);
        this.amount = amount;
    }

    public void calculateBettingResult(MatchResult matchResult) {
        if (matchResult == MatchResult.WIN_WITH_BLACKJACK) {
            amount = (int) (amount * 1.5);
            return;
        }
        if (matchResult == MatchResult.LOSE) {
            amount = -amount;
            return;
        }
        if (matchResult == MatchResult.DRAW) {
            amount = amount - amount;
        }
    }

    public int getAmount() {
        return amount;
    }

    private void validateBetAmount(int amount) {
        if (amount > 100_000_000 || amount <= 0) {
            throw new IllegalArgumentException("베팅 금액에 문제가 있음");
        }
    }
}
