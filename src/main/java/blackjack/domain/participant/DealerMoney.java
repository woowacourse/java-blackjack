package blackjack.domain.participant;

public class DealerMoney {
    private int money;

    public DealerMoney() {
        money = 0;
    }

    public void calculateByOpponentProfit(final int opponentProfit) {
        money = money - opponentProfit;
    }

    public int getMoney() {
        return money;
    }
}
