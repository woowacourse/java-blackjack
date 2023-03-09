package domain;

public class BettingMoney {

    private final int value;

    public BettingMoney(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        validateIsBiggerThanMinimum(value);
        validateIsAppropriateUnit(value);
    }

    private void validateIsBiggerThanMinimum(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0원보다 커야합니다.");
        }
    }

    private void validateIsAppropriateUnit(int value) {
        if (value % 1000 != 0) {
            throw new IllegalArgumentException("베팅은 1000원 단위로 가능합니다.");
        }
    }

    public int value() {
        return value;
    }
}
