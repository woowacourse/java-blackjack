package domain.money;

public class BetAmount {
    private static final String NOT_INTEGER = "배팅 금액은 정수만 가능합니다.";
    private static final String UNDER_MIN_AMOUNT = "배팅 금액은 0보다 커야 합니다.";
    private static final int MIN_AMOUNT = 0;

    private final int amount;

    private BetAmount(int amount) {
        validateMinAmount(amount);
        this.amount = amount;
    }

    public static BetAmount from(int amount) {
        return new BetAmount(amount);
    }

    public static BetAmount from(String input) {
        try {
            int amount = Integer.parseInt(input);
            return new BetAmount(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER);
        }
    }

    private void validateMinAmount(double amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException(UNDER_MIN_AMOUNT);
        }
    }

    public Profit calculateEarnAmount(double rate) {
        return new Profit(amount * rate);
    }

    public double getAmount() {
        return amount;
    }
}
