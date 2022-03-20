package blackjack;

public class Receipt {
    private static final int MINIMUM = 1000;
    private static final double BLACKJACK_RATIO = 1.5;
    private static final String BETTING_MONEY_MINIMUM_EXCEPTION = "[ERROR] 최소베팅금액은 1000원입니다.";
    private final int money;

    private Receipt(int money) {
        this.money = money;
    }

    public static Receipt empty() {
        return new Receipt(0);
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

    public static Receipt opposite(Receipt receipt) {
        return new Receipt(-receipt.money);
    }

    public static Receipt merge(Receipt receipt1, Receipt receipt2) {
        return new Receipt(receipt1.money + receipt2.money);
    }

    public static Receipt blackjack(Receipt receipt) {
        return new Receipt((int)(receipt.money()*BLACKJACK_RATIO));
    }

    public int money() {
        return money;
    }
}
