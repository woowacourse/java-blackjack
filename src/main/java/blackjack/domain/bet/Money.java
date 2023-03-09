package blackjack.domain.bet;

public class Money {

    private static final int UNIT_AMOUNT = 1_000;
    private static final int MIN_AMOUNT = 1_000;
    private static final int MAX_AMOUNT = 100_000;
    static final String OUT_OF_RANGE_EXCEPTION_MESSAGE = MIN_AMOUNT + "이상 " + MAX_AMOUNT + "이하여야 합니다.";
    static final String UNIT_AMOUNT_EXCEPTION_TEST = UNIT_AMOUNT + "원 단위로 입력해야 합니다.";

    private final int amount;

    public Money(final int amount) {
        validateRange(amount);
        validateUnitAmount(amount);
        this.amount = amount;
    }

    private void validateUnitAmount(final int amount) {
        if (amount % UNIT_AMOUNT != 0) {
            throw new IllegalArgumentException(UNIT_AMOUNT_EXCEPTION_TEST);
        }
    }

    private void validateRange(final int amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new IllegalArgumentException(OUT_OF_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
