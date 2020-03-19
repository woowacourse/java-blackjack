package domain.profit;

public class PlayerLoose implements ProfitStrategy {
    private static final double LOOSE_RATE = -1d;

    @Override
    public double getProfit(int bettingMoney) {
        return (LOOSE_RATE * (double) bettingMoney);
    }
}
