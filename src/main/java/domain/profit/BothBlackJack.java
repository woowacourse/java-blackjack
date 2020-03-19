package domain.profit;

public class BothBlackJack implements ProfitStrategy {
    @Override
    public double getProfit(int bettingMoney) {
        return bettingMoney;
    }
}
