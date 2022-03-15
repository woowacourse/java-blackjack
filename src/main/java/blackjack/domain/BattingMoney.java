package blackjack.domain;

public class BattingMoney {
    public static final int BATTING_MIN_VALUE = 1;
    private final int value;

    public BattingMoney(int value) {
        checkNegative(value);
        this.value = value;
    }

    private void checkNegative(int value) {
        if (value < BATTING_MIN_VALUE) {
            throw new IllegalArgumentException("배팅 금액 최소 금액은 1원입니다.");
        }
    }
}
