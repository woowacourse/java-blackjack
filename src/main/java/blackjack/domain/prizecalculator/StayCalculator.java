package blackjack.domain.prizecalculator;

import static blackjack.domain.card.Cards.MAX_SCORE;

public class StayCalculator implements PrizeCalculator {

    private static final StayCalculator INSTANCE = new StayCalculator();
    private static final int WIN_EARNING_RATE = 1;
    private static final int LOSS_EARNING_RATE = -1;
    private static final int PUSH_EARNING_RATE = 0;

    private StayCalculator() {
    }

    public static StayCalculator getInstance() {
        return INSTANCE;
    }

    @Override
    public double calculate(final int playerScore, final int dealerScore, final boolean dealerBlackjack,
                            final double bettingAmount) {
        if (dealerScore > MAX_SCORE) {
            return bettingAmount * WIN_EARNING_RATE;
        }
        if (playerScore > dealerScore) {
            return bettingAmount * WIN_EARNING_RATE;
        }
        if (playerScore < dealerScore) {
            return bettingAmount * LOSS_EARNING_RATE;
        }

        return bettingAmount * PUSH_EARNING_RATE;
    }
}
