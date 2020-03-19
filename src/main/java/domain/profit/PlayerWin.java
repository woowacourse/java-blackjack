package domain.profit;

public class PlayerWin implements ProfitStrategy {
    @Override
    public double getProfit(int bettingMoney) {
        return bettingMoney;
    }
}
