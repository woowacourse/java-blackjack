package model.betting;

public class BlackJacCalculateStrategy implements BettingCalculateStrategy {

    private static final double BLACKJACK_WIN_BET_RATE = 1.5;

    @Override
    public long calculate(long bettingAmount) {
        return (long) (bettingAmount * BLACKJACK_WIN_BET_RATE);
    }
}
