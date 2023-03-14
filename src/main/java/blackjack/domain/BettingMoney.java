package blackjack.domain;

import java.math.BigInteger;

public class BettingMoney {
    private static final int UNIT = 100;
    private final BigInteger value;

    public BettingMoney(final int value) {
        validate(value);
        this.value = BigInteger.valueOf(value);
    }

    private void validate(final int value) {
        validateNotPositive(value);
        validateInappropriateUnit(value);
    }

    private void validateNotPositive(int uncheckedValue) {
        if (uncheckedValue <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야 합니다.");
        }
    }

    private void validateInappropriateUnit(int uncheckedValue) {
        if(uncheckedValue % UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 100 단위어야 합니다.");
        }
    }

    public int getValue() {
        return value.intValue();
    }
}
