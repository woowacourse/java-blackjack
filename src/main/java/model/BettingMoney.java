package model;

public class BettingMoney {

    private final int value;

    public BettingMoney(int value) {
        validatePositiveNumber(value);
        this.value = value;
    }

    private void validatePositiveNumber(Integer bettingAmount) {
        if (bettingAmount <= 0) {
            throw new IllegalArgumentException("배팅금액은 양수가 되어야 합니다.");
        }
    }

    public int toInt() {
        return value;
    }
}
