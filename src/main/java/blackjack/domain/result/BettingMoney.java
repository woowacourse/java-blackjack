package blackjack.domain.result;

public abstract class BettingMoney {

    protected static final int MONEY_ZERO = 0;

    private final int money;

    public BettingMoney(int money) {
        this.money = money;
    }

    public boolean isEmpty() {
        return money == MONEY_ZERO;
    }

    public int getMoney() {
        return money;
    }
}
