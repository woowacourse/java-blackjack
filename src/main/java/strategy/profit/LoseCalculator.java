package strategy.profit;

import bet.BetAmount;

public class LoseCalculator implements ProfitCalculator {
    @Override
    public double calculate(BetAmount betAmount) {
        return -betAmount.getValue();
    }
}
