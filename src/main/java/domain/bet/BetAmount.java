package domain.bet;

public class BetAmount {

    private final static int MAX_AMOUNT = 1_000_000;
    private final int value;

    public BetAmount(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        validateRange(value);
    }

    private void validateRange(final int value) {
        if (value > MAX_AMOUNT) {
            throw new IllegalArgumentException(String.format("배팅 금액: %d, 배팅 금액은 최대 %d원입니다", value, MAX_AMOUNT));
        }
    }

}
