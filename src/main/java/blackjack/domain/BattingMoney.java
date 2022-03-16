package blackjack.domain;

public class BattingMoney {
    public static final int BATTING_MIN_VALUE = 0;
    private final int value;

    public BattingMoney(int value) {
        checkNegative(value);
        this.value = value;
    }

    public BattingMoney(String value) {
        this(Integer.parseInt(value));
    }

    private void checkNegative(int value) {
        if (value < BATTING_MIN_VALUE) {
            throw new IllegalArgumentException("배팅 금액 최소 금액은 0원입니다.");
        }
    }
}
