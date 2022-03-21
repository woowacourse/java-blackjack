package blackjack.domain;

public class BettingMoney {

    private static final int BETTING_MONEY_INIT_VALUE = 0;

    private int value;

    public BettingMoney(int value) {
        checkNegative(value);
        this.value = value;
    }

    public BettingMoney() {
        this.value = BETTING_MONEY_INIT_VALUE;
    }

    public BettingMoney(String value) {
        this(Integer.parseInt(value));
    }

    private void checkNegative(int value) {
        if (value < BETTING_MONEY_INIT_VALUE) {
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
