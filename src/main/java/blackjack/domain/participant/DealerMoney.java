package blackjack.domain.participant;

public class DealerMoney {
    private double money;

    public DealerMoney() {
        money = 0;
    }

    public void resetMoneyByOpponentProfit(final double opponentProfit) {
        money = money - opponentProfit;
    }

    public double getMoney() {
        return money;
    }
}
