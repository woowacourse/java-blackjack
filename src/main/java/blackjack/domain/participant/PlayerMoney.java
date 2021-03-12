package blackjack.domain.participant;

public class PlayerMoney {
    private double money;

    public PlayerMoney(final double money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(final double money) {
        if (money < 0) {
            throw new IllegalArgumentException("베팅 금액은 0 이상이여야 합니다.");
        }
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
