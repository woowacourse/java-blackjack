package model.betting;

public class NormalCalculateStrategy implements BettingCalculateStrategy {
    @Override
    public long calculate(long bettingAmount) {
        return bettingAmount;
    }
}
