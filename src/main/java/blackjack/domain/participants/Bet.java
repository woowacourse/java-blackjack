package blackjack.domain.participants;

import blackjack.domain.game.GameResult;

public class Bet {
    private final long amount;

    public static Bet zero() {
        return new Bet(0L);
    }

    public Bet(long amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private static void validatePositive(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    public long calculateProfit(GameResult gameResult) {
        return gameResult.calculateProfit(amount);
    }
}
