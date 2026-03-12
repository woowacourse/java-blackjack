package domain;

public class Betting {
    public static final String ERROR_BET_MUST_BE_POSITIVE = "[ERROR] 베팅 금액은 0보다 커야 합니다.";

    private final int amount;

    private Betting(int amount) {
        this.amount = amount;
    }

    public static Betting none() {
        return new Betting(0);
    }

    public static Betting of(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ERROR_BET_MUST_BE_POSITIVE);
        }
        return new Betting(amount);
    }

    public int amount() {
        return amount;
    }

    public double settle(WinningStatus status) {
        return switch (status) {
            case BLACKJACK_WIN -> amount + amount * 1.5;
            case WIN -> amount + amount;
            case TIE -> amount;
            case LOSE -> 0;
        };
    }
}
