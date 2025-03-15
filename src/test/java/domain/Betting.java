package domain;

public class Betting {
    private final int amount;

    public Betting(final int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private static void validatePositive(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 음수가 될 수 없습니다.");
        }
    }

    public int calculateBettingOfReturn(final GameResult gameResult, final boolean isBlackjack) {
        return 0;
    }
}
