package domain.bettingMoney;

public class Money {
    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Money multiply(double multipleValue) {
        Double resultValue = value * multipleValue;
        return new Money(resultValue.intValue());
    }

    @Override
    public boolean equals(Object obj) {
        Money other = (Money) obj;
        return value == other.value;
    }
}
