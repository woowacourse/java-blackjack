package blackjack.domain.participant;

import blackjack.domain.bet.PayoutPolicy;

public class DrawPayoutPolicy implements PayoutPolicy {

    private static final double DRAW_PAYOUT_COEFFICIENT = 1.0D;

    @Override
    public int payout(final int amount) {
        return (int) (amount * DRAW_PAYOUT_COEFFICIENT);
    }
}
