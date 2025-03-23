package strategy.profit;

import bet.BetAmount;

public interface ProfitCalculator {

    double calculate(BetAmount betAmount);
}
