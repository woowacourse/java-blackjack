package bet;

import result.MatchResult;

public class Bet {
    private final int amount;

    private Bet(int amount) {
        this.amount = amount;
    }

    public Bet calculateBettingResult(MatchResult matchResult) {
        if (matchResult == MatchResult.WIN_WITH_BLACKJACK) {
            return new Bet((int) (amount * 1.5));
        }
        if (matchResult == MatchResult.LOSE) {
            return new Bet(-amount);
        }
        if (matchResult == MatchResult.DRAW) {
            return new Bet(amount - amount);
        }
        return new Bet(amount);
    }

    public int getAmount() {
        return amount;
    }

    public static Bet createInitialBet(int amount) {
        if (amount > 100_000_000 || amount <= 0) {
            throw new IllegalArgumentException("베팅 금액에 문제가 있음");
        }

        return new Bet(amount);
    }
}
