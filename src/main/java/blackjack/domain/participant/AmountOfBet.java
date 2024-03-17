package blackjack.domain.participant;

import java.util.Objects;

public class AmountOfBet {
    private static final int MIN_VALUE = 1000;

    private final int value;

    public AmountOfBet(int value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException(String.format("베팅금액은 1,000원 이상의 양수만 가능합니다. 입력값: %d", value));
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AmountOfBet amountOfBet)) {
            return false;
        }
        return value == amountOfBet.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
