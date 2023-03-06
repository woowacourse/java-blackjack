package domain;

public class Bet {

    private static final int MIN_BET = 0;
    private static final int MAX_BET = 100_000;
    private final int value;

    public Bet(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (value < MIN_BET) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0 미만일 수 없습니다.");
        }
        if (value > MAX_BET) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 100,000 초과일 수 없습니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
