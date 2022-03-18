package blackjack.domain.result;

public class BettingMoney {

    private static final String ERROR_MESSAGE_INVALID_MONEY = "배팅 금액을 넣어주세요.";
    private static final int MONEY_ZERO = 0;

    private final int money;

    public BettingMoney(int money) {
        checkValidProfit(money);
        this.money = money;
    }

    private static void checkValidProfit(int money) {
        if (money <= MONEY_ZERO) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_MONEY);
        }
    }

    public int getMoney() {
        return money;
    }
}
