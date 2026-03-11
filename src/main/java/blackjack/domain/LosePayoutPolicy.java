package blackjack.domain;

public class LosePayoutPolicy implements PayoutPolicy {

    private static final int LOSE_PAYOUT_COEFFICIENT = 0;

    @Override
    public int payout(final int amount) {
        return amount * LOSE_PAYOUT_COEFFICIENT;
    }
}
