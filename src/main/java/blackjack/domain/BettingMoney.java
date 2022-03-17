package blackjack.domain;

public class BettingMoney {

    public static final int BATTING_MIN_VALUE = 0;

    private int value;

    public BettingMoney(int value) {
        checkNegative(value);
        this.value = value;
    }

    public BettingMoney(String value) {
        this(Integer.parseInt(value));
    }

    private void checkNegative(int value) {
        if (value < BATTING_MIN_VALUE) {
            throw new IllegalArgumentException("배팅 금액 최소 금액은 0원입니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public boolean addMoney(int money) {
        value += money;
        return true;
    }
}
