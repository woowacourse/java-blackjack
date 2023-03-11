package blackjack.domain.participant;

import java.util.Objects;

public class Amount {

    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    private static void validateZero(int value) {
        if (value == 0) {
            throw new IllegalArgumentException("1원 이상의 배팅금액을 입력해주세요.");
        }
    }

    public Amount updateAmount(int amount) {
        validateZero(amount);
        return new Amount(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value == amount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}
