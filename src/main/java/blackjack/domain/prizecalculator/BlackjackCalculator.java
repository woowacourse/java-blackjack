package blackjack.domain.prizecalculator;

public class BlackjackCalculator implements PrizeCalculator {

    private static final BlackjackCalculator INSTANCE = new BlackjackCalculator();
    private static final double WIN_EARNING_RATE = 1.5;
    private static final int PUSH_EARNING_RATE = 0;

    private BlackjackCalculator() {
    }

    public static BlackjackCalculator getInstance() {
        return INSTANCE;
    }

    @Override
    public double calculate(final int playerScore, final int dealerScore, final boolean dealerBlackjack,
                            final double bettingAmount) {
        if (dealerBlackjack) {
            return bettingAmount * PUSH_EARNING_RATE;
        }
        return bettingAmount * WIN_EARNING_RATE;
    }
}
