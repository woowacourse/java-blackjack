package balckjack.domain;

public class Money {

    private final int value;

    public Money(int value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(
                String.format("베팅 금액은 0보다 커야 합니다. 들어온 금액 : %d", value));
        }
    }

    public double applyRate(Result result) {
        return value * result.getRate();
    }

}

