package blackjack.domain;

public class Money {

    private final Double value;

    public Money(Double value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(Double value) {
        if (value <= 0) {
            throw new IllegalArgumentException(
                String.format("베팅 금액은 0보다 커야 합니다. 들어온 금액 : %d", value.longValue()));
        }
    }

    public Double applyRate(Result result) {
        return value * result.getRate();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

