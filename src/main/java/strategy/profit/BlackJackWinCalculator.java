package strategy.profit;

import bet.BetAmount;

public class BlackJackWinCalculator implements ProfitCalculator {
    @Override
    public double calculate(BetAmount betAmount) {
        return betAmount.getValue() * 1.5;
    }
}
