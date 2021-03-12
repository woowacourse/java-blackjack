package blackjack.domain.participant;

public class PlayerMoney {
    private int money;

    public PlayerMoney(final int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("베팅 금액은 0 이상이여야 합니다.");
        }
    }

    public double calculateProfit(final double profitRate) {
        final int profit = (int) (money * profitRate);
        calculateMoneyByProfit(profit);
        return profit;
    }

    private void calculateMoneyByProfit(final int profit) {
        money = money + profit;
    }
}
