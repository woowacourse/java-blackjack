package bet;

import java.util.Objects;

public class Money {

    private static final long MONEY_UNIT = 100L;
    private static final long MIN_MONEY = 1000L;
    private final long value;


    public Money(long value) {
//        validate(value);
        this.value = value;
    }

    /*private void validate(long value) {
        validateMin(value);
        validateUnit(value);
    }

    private void validateMin(long value) {
        if (value < MIN_MONEY) {
            throw new IllegalArgumentException("배팅 금액은 1000원 이상부터 가능합니다.");
        }
    }

    private void validateUnit(long value) {
        if (value % MONEY_UNIT != 0L) {
            throw new IllegalArgumentException("배팅 금액은 100원 단위만 가능합니다.");
        }
    }*/

    public Money times(double rate) {
        return new Money((long) (value * rate));
    }

    public Money plus(Money money) {
        return new Money(money.value + value);
    }

    public long getValue() {
        return value;
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

    @Override
    public String toString() {
        return value + "";
    }
}
