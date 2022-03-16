package domain.betting;

public class Money {

    private static final String KEEP_BETTING_MONEY_POSITIVE_ERROR_MESSAGE = "[Error] 돈은 음수가 될 수 없습니다.";

    private final int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(KEEP_BETTING_MONEY_POSITIVE_ERROR_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }
}
