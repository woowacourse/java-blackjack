package bet;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        validateMin(value);
        validateUnit(value);
    }

    private void validateMin(int value) {
        if (value < 1000) {
            throw new IllegalArgumentException("배팅 금액은 1000원 이상부터 가능합니다.");
        }
    }

    private void validateUnit(int value) {
        if (value % 1000 != 0) {
            throw new IllegalArgumentException("배팅 금액은 1000원 단위만 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
