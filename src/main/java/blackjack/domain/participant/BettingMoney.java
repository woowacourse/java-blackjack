package blackjack.domain.participant;

public class BettingMoney {
    private static final String MONEY_ERROR_EXCEPTION_MESSAGE = "0원 초과로 배팅을 해야 합니다.";
    private final int money;

    public BettingMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(MONEY_ERROR_EXCEPTION_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }
}
