package strategy.profit;

import bet.BetAmount;

public class WinCalculator implements ProfitCalculator {
    @Override
    public double calculate(BetAmount betAmount) {
        return betAmount.getValue();
    }
}
