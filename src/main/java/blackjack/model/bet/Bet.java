package blackjack.model.bet;

public final class Bet {
    private static final String ERROR_NEGATIVE = "[ERROR] 배팅 금액은 음수일 수 없습니다.";
    private static final String ERROR_UNIT = "[ERROR] 배팅 금액은 1000원 단위여야 합니다.";
    private static final int UNIT = 1000;

    private final Amount amount;

    private Bet(Amount amount) {
        this.amount = amount;
    }

    public static Bet from(int amount) {
        validateAmount(amount);
        return new Bet(new Amount(amount));
    }

    private static void validateAmount(int amount) {
        checkNegative(amount);
        checkUnit(amount);
    }

    private static void checkNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    private static void checkUnit(int amount) {
        if ((amount % UNIT) != 0) {
            throw new IllegalArgumentException(ERROR_UNIT);
        }
    }

    public Amount profitOf(Result result) {
        return result.apply(this.amount);
    }
}
