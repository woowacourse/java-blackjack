package domain.manager;

public class Bet {

    private final int value;

    public Bet(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("[ERROR] 0원 이상의 금액을 배팅해주세요.");
        }
    }

    public int getValue() {
        return value;
    }
}
