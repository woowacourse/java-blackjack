package domain.participant;

public class BettingMoney {
    public static final String BOUNDARY_ERROR_MESSAGE = "[ERROR] 베팅 금액이 0보다 작을 수 없습니다.";
    public static final String UNIT_ERROR_MESSAGE = "[ERROR] 베팅 금액은 1000원 단위여야 합니다.";

    private final int value;

    public BettingMoney(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        validateBoundary(value);
        validateUnit(value);
    }

    private void validateBoundary(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException(BOUNDARY_ERROR_MESSAGE);
        }
    }

    private void validateUnit(final int value) {
        if (value % 1000 != 0) {
            throw new IllegalArgumentException(UNIT_ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
