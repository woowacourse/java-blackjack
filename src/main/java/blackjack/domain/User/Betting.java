package blackjack.domain.User;

public class Betting {
    private static final int MIN_MONEY_VALUE = 0;
    private static final double BLACK_JACK_MUL = 1.5;

    private final int amount;

    private Betting(int amount) {
        validateNaturalNumber(amount);
        this.amount = amount;
    }

    private void validateNaturalNumber(int amount) {
        if (amount <= MIN_MONEY_VALUE) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 0보다 커야됩니다.");
        }
    }

    public double getBlackJackRevenue() {
        return amount * BLACK_JACK_MUL - amount;
    }

    public double getAmount() {
        return amount;
    }

    public static Betting from(int amount) {
        return new Betting(amount);
    }


    public double getLoseRevenue() {
        return -amount;
    }

    public double getDrawRevenue() {
        return 0;
    }
}
