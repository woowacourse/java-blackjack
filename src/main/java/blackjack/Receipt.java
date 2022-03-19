package blackjack;

public class Receipt {
    private static final int MINIMUM = 1000;
    private static final String BETTING_MONEY_MINIMUM_EXCEPTION = "[ERROR] 최소베팅금액은 1000원입니다.";
    private final int money;

    private Receipt(int money) {
        this.money = money;
    }

    public static Receipt generate(int money) {
        validateMinimum(money);
        return new Receipt(money);
    }

    private static void validateMinimum(int money) {
        if (money < MINIMUM) {
            throw new IllegalArgumentException(BETTING_MONEY_MINIMUM_EXCEPTION);
        }
    }
}
