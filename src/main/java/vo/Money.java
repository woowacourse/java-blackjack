package vo;

public class Money {
    private static final String ERROR_LESS_THAN_MINIMUM_MONEY = "[ERROR] 배팅액을 1원 이상으로 입력해주세요. ";
    private static final int MINIMUM_MONEY = 0;
    private final int money;

    private Money(int money) {
        this.money = money;
        validateIsPositive(money);
    }

    private void validateIsPositive(int money) {
        if (money < MINIMUM_MONEY) {
            throw new IllegalArgumentException(ERROR_LESS_THAN_MINIMUM_MONEY + money);
        }
    }

    public static Money from(int money) {
        return new Money(money);
    }

    public int getMoney() {
        return money;
    }
}
