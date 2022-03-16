package blackjack.domain;

public class BetMoney {
    private static final String INVALID_RANGE_MESSAGE = "배팅 금액이 정상 범위가 아닙니다";
    private static final int LOWER_BOUND = 0;
    private final Integer value;

    public BetMoney(final Integer value) {
        checkBetMoneyRange(value);
        this.value = value;
    }

    private void checkBetMoneyRange(final Integer betMoney) {
        if (betMoney < LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_RANGE_MESSAGE);
        }
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final BetMoney betMoney = (BetMoney) object;

        return value != null ? value.equals(betMoney.value) : betMoney.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
