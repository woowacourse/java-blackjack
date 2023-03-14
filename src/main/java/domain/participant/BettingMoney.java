package domain.participant;

public class BettingMoney {

    private static final int MINIMUM_UNIT_OF_MONEY = 1000;
    private static final int MINIMUM_BETTING_MONEY = 0;
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
        if (value <= MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException("베팅 금액은 0원보다 커야합니다.");
        }
    }

    private void validateIsAppropriateUnit(int value) {
        if (isAppropriateUnit(value)) {
            throw new IllegalArgumentException("베팅은 1000원 단위로 가능합니다.");
        }
    }

    private boolean isAppropriateUnit(int value) {
        return value % MINIMUM_UNIT_OF_MONEY != 0;
    }

    public int value() {
        return value;
    }
}
