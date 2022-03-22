package blackjack.domain.result;

public class EmptyBettingMoney implements BettingMoney {

    private static final int MONEY_ZERO = 0;

    private final int money;

    public EmptyBettingMoney() {
        this.money = MONEY_ZERO;
    }

    @Override
    public boolean isEmpty() {
        return money == MONEY_ZERO;
    }

    @Override
    public int getMoney() {
        return money;
    }
}
