package blackjack.domain.participant.playerstatus;

import static blackjack.domain.card.Cards.MAX_SCORE;

public final class Stay extends CalculableStatus {

    private static final Stay INSTANCE = new Stay();
    private static final int WIN_EARNING_RATE = 1;
    private static final int LOSS_EARNING_RATE = -1;
    private static final int PUSH_EARNING_RATE = 0;

    private Stay() {
    }

    public static Stay getInstance() {
        return INSTANCE;
    }

    @Override
    public double calculateProfit(final int playerScore, final int dealerScore, final boolean dealerBlackjack,
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
