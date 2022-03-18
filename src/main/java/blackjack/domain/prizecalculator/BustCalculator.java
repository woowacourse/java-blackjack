package blackjack.domain.prizecalculator;

public class BustCalculator implements PrizeCalculator {

    private static final BustCalculator INSTANCE = new BustCalculator();
    private static final int EARNING_RATE = -1;

    private BustCalculator() {
    }

    public static BustCalculator getInstance() {
        return INSTANCE;
    }

    @Override
    public double calculate(final int playerScore, final int dealerScore, final boolean dealerBlackjack,
                            final double bettingAmount) {
        return bettingAmount * EARNING_RATE;
    }
}
