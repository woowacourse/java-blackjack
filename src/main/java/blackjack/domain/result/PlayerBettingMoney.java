package blackjack.domain.result;

public class PlayerBettingMoney extends BettingMoney {

    private static final String ERROR_MESSAGE_INVALID_MONEY = "배팅 금액을 넣어주세요.";

    public PlayerBettingMoney(int money) {
        super(money);
        checkValidProfit(money);
    }

    public PlayerBettingMoney(BettingMoney bettingMoney) {
        super(bettingMoney.getMoney());
    }

    private static void checkValidProfit(int money) {
        if (money <= MONEY_ZERO) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_MONEY);
        }
    }
}
