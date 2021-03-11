package blackjack.domain.participant;

public class PlayerMoney {
    private double money;

    public PlayerMoney(final double money) {
        this.money = money;
    }

    public double calculateProfit(final double profitRate) {
        final double profit = money * profitRate;
        calculateMoneyByProfit(profit);
        return profit;
    }

    private void calculateMoneyByProfit(final double profit) {
        money = money + profit;
    }
}
