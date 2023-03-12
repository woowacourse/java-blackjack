package domain.participant;

public class BettingMoney {
    public static final String BOUNDARY_ERROR_MESSAGE = "[ERROR] 베팅 금액이 0보다 작을 수 없습니다.";

    private final int value;

    public BettingMoney(final int value) {
        validateBoundary(value);
        this.value = value;
    }

    private void validateBoundary(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException(BOUNDARY_ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
