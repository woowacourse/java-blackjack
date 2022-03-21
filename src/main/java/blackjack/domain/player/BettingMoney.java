package blackjack.domain.player;

public class BettingMoney {

    private static final int EMPTY_MONEY = 0;

    private final int value;

    public BettingMoney(int value) {
        checkNegative(value);
        this.value = value;
    }

    public static BettingMoney emptyMoney() {
        return new BettingMoney(EMPTY_MONEY);
    }

    private void checkNegative(int money) {
        if (money < EMPTY_MONEY) {
            throw new IllegalArgumentException("베팅 금액은 양수로 입력해주세요.");
        }
    }

    public int getValue() {
        return value;
    }
}
